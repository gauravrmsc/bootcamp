#!/bin/bash

if [ $# -ne 1 ]
then
  echo "USAGE: $(basename $0) <service_git_dir>" >&2
  exit -1
fi

svc_dir="$1"
if [ ! -d "$svc_dir" ]
then
  echo "service_git_dir $svc_dir is not present" >&2
  exit
fi
if [ "$svc_dir" == "." ]
then
  svc_dir=$(pwd)
fi
if [ "$svc_dir" == ".." ]
then
  svc_dir="$(pwd)/.."
fi

install_dir=$(dirname $0)
if [ "$install_dir" == "." ]
then
  install_dir=$(pwd)
fi
if [ "$install_dir" == ".." ]
then
  install_dir="$(pwd)/.."
fi

if [[ -z "${BITBUCKET_REPO_SLUG}" ]]
then
  svc=$(basename $svc_dir)
  if [ -z "$svc" ]
  then
    echo "no service found, please check if this is git repo dir" >&2
    exit
  fi
else
  svc="${BITBUCKET_REPO_SLUG}"
fi

cd $svc_dir
if [[ -z "${BITBUCKET_COMMIT}" ]]
then
  git_sha=$(git log --oneline | head -1 | awk '{print $1}')
  if [ -z "$git_sha" ]
  then
    echo "no commit found, please check if this is git repo dir" >&2
    exit
  fi
else
  git_sha=$(echo "${BITBUCKET_COMMIT}" | cut -c1-10)
fi

cd $install_dir
which yasha >/dev/null 2>&1
if [ $? -ne 0 ]
then
  echo "Please install yasha. $ pip install yasha"
  exit
fi

which jq >/dev/null 2>&1
if [ $? -ne 0 ]
then
  echo "Please install jq" >&2
  exit
fi

if [[ -z "${BITBUCKET_COMMIT}" ]]
then
  echo -n ""
else
  prewd=$(pwd)
  ls -l $svc_dir/codepipeline/basic_tag_replace2.sh
  if [ -e "/codepipeline/install.sh" ]
  then
    cd /codepipeline/
    ./install.sh $svc_dir
  fi
  cd $svc_dir
  echo "$svc_dir $prewd"
  ls -l $svc_dir/codepipeline/basic_tag_replace2.sh
  $svc_dir/codepipeline/basic_tag_replace2.sh $svc_dir
  exit $?
fi

config=$(cat ops_configs/config.json | jq ".")

cmd="jq '.service_name = \"$svc\"'"
config=$(echo "$config" | eval $cmd)
cmd="jq '.git_tag = \"$git_sha\"'"
config=$(echo "$config" | eval $cmd)
echo $config | jq "." > ops_configs/config.vars.json

dest_dir="$svc_dir/codepipeline"
mkdir -p "$dest_dir"
for f in $(ls templates/*.j2 | grep -v cloudformation)
do
  echo "Processing $f ..."
  dest=$(echo "$f" | sed -e 's/^templates//' -e 's/\.j2//')
  yasha -v ops_configs/config.vars.json -v configs/common.yaml -v ops_configs/common.yaml -o "$dest_dir/$dest" $f
done
for f in $(ls templates/*.j2 | grep cloudformation)
do
  echo "Processing $f ..."
  dest=$(echo "$f" | sed -e 's/^templates//' -e 's/\.j2//')
  yasha -v ops_configs/config.vars.json -v configs/common.yaml -v ops_configs/common.yaml -v configs/stage.yaml -v ops_configs/stage.yaml -o "$dest_dir/$dest" $f
  if [ -f "ops_configs/prod.yaml" ]
  then
    #yasha -v ops_configs/config.vars.json -v configs/common.yaml -v ops_configs/common.yaml -v ops_configs/prod.yaml -o "$dest_dir/$dest" $f
    echo -n ""
  fi
done
echo "Processing configs/buildspec.yml $dest_dir/buildspec.yml ..."
yasha -v ops_configs/config.vars.json -v configs/common.yaml -v ops_configs/common.yaml -o $dest_dir/buildspec.yml configs/buildspec.yml

## Add in .gitignore
gitignore="$svc_dir/.gitignore"
if [ -e "$gitignore" ]
then
if [ $(grep -c "^#===codepipeline===#" $gitignore) -le 0 ]
then
  echo '#===codepipeline===#' >> $gitignore
  echo 'codepipeline/*.yml' >> $gitignore
  echo 'codepipeline/ops_configs/config.vars.json' >> $gitignore
  echo 'codepipeline/run_at_bitbucket.sh' >> $gitignore
  echo 'codepipeline/.*' >> $gitignore
fi
fi
mv "$dest_dir/bitbucket-pipelines.yml" "$svc_dir/"

run_at_bitbucket="$svc_dir/codepipeline/run_at_bitbucket.sh"
if [ -e "$run_at_bitbucket" ]
then
  chmod u+x $run_at_bitbucket
  [[ -z "${BITBUCKET_COMMIT}" ]] || $run_at_bitbucket $svc_dir
  exit $?
fi
exit 0