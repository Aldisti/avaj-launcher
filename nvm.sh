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

# This method disables the catch any error (set -e)
# 1: scenario to execute, no default
_execute() {
    if [ -z "$1" ]; then
        error "_execute received no arguments"
    fi
    local scenario="$1"

    set +e # undos the `set -e` command

    java -cp target net.aldisti.avaj.Simulator ${scenario}
    local ret="$?"

    return $ret
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

    _execute ${scenario}
    local ret="$?"
    set -e

    if [ $ret -eq 0 ]; then
        info "Program executed successfully"
    else
        error "Program exited with code ${ret}"
    fi
}

test() {
    local total_tests="$(find scenarios -name "invalid*.txt" | wc -l)"
    local ok_tests=0

    info "Running ${total_tests} tests..."

    for scenario in $(find scenarios -name "invalid*.txt" | tr '\n' ' '); do
        _execute ${scenario} > /dev/null

        if [ $? -eq 0 ]; then
            echo -e " > ${RED}KO${RESET} ${scenario}"
        else
            echo -e " > ${GREEN}OK${RESET} ${scenario}"
            ok_tests=$((ok_tests + 1))
        fi
    done
    set -e

    info "Successful tests ${ok_tests}/${total_tests}"
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
    echo -e "  test                   Runs the program against all invalid scenarios."
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
        test)
            clean
            compile
            test
            clean
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
