#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=mysort2GB.log

echo 'start mysort'
time java mainExtenalSort /input/data-2GB.in /tmp

echo 'start valsort'
valsort /tmp/pass4run0
