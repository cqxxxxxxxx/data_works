#!/bin/sh
set -e

function image() {
    read -p "是否跳过镜像构建?(y/n)" SKIP_BUILD
    if [ "$SKIP_BUILD" != "y" ]; then
        read -p "输入镜像版本号(例如0.0.1)" IMAGE_VERSION
        cd $1
        if [ -z "$IMAGE_VERSION" ]; then
          docker build -t stt/data-works:latest .
        else
          docker build -t stt/data-works:latest -t stt/data-works:"$IMAGE_VERSION" .
        fi
    else
        echo "跳过镜像构建"
    fi
    return
}

function deploy() {
    echo "========================================================"
    echo "=                    启动服务                           ="
    echo "========================================================"
    cd $1
    docker-compose -f docker-compose.yml up -d
}

DEPLOY_ROOT=$(pwd)
image "$DEPLOY_ROOT"
deploy "$DEPLOY_ROOT"