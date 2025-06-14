#!/bin/bash

RESET="\033[0m"
BLACK="\033[1;30m"
RED="\033[1;31m"
GREEN="\033[1;32m"
YELLOW="\033[1;33m"
BLUE="\033[1;34m"
PURPLE="\033[1;35m"
CYAN="\033[1;36m"
WHITE="\033[1;37m"


# 1: type of log
# 2: log message
# 3: color
_log() { echo -e "$3$1${RESET}: $2"; }
info() { _log "INFO" "$1" "${GREEN}"; }
warning() { _log "WARNING" "$1" "${YELLOW}"; }
error() { _log "ERROR" "$1" "${RED}"; }

set -e

# no args
compile() {
    find * -name "*.java" > .sources
    javac -d target @.sources
    rm .sources
}

# no args
clean() {
    if [ -d target ]; then
        rm -r target
        info "Target removed"
    fi
}


while getopts ":cC" opt; do
    case ${opt} in
        c)
            compile
            ;;
        C)
            clean
            ;;
        ?)
            error "Invalid option: ${opt}"
            exit 1
        ;;
    esac
done
