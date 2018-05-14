#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=mysort20GB.log

echo 'start mysort'
time java mainExtenalSort /input/data-20GB.in /tmp

echo 'start valsort'
valsort /tmp/pass5run0
