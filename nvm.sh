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
    local sources_file=".sources"
    find * -name "*.java" > ${sources_file}
    javac -d target @${sources_file}
    info "Compiled $(cat ${sources_file} | wc -l) classes"
    rm .sources
}

# no args
clean() {
    if [ -d target ]; then
        rm -r target
        info "Target removed"
    fi
}

# 1: scenario to execute, default value is: scenarios/scenario.txt
run() {
    if ! [ -d target ]; then
        compile
    fi

    local scenario="$1"
    if [ "${scenario}" == "" ]; then
        local scenario="scenarios/scenario.txt"
    fi

    set +e # undos the `set -e` command
    java -cp target net.aldisti.avaj.Simulator ${scenario}
    local ret="$?"
    set -e # exit at the first cammand that fails

    if [ $ret -eq 0 ]; then
        info "Program executed successfully"
    else
        error "Program exited with code ${ret}"
    fi
}

help() {
    echo -e "A custom version of mvn (Maven)."
    echo
    echo -e "usage: ./nvm.sh [<goal(s)>]"
    echo
    echo -e "Goals:"
    echo -e "  clean                  Removes all compiled files."
    echo -e "  compile                Compiles all the java files and puts them inside ./target."
    echo -e "  run <scenario>         Compiles if not already done, and then executes the java program"
    echo -e "                         using the scenario passed as argument, or scenario.txt if nothing is passed."
    echo -e "  re                     Cleans, compiles and then runs the java program."
    echo -e "  help                   Shows this page."
    echo
}

while [ $# -gt 0 ]; do
    opt="$(echo $1 | tr 'A-Z' 'a-z')"
    shift
    case ${opt} in
        clean)
            clean
            ;;
        compile)
            compile
            ;;
        recompile)
            clean
            compile
            ;;
        run)
            if [[ "$1" == *txt ]]; then
                scenario="$1"
                shift
            fi
            run ${scenario}
            ;;
        re)
            clean
            run
            ;;
        help)
            help
            ;;
        ?)
            error "Invalid argument: ${opt}"
            exit 1
        ;;
    esac
done
