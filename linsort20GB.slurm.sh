#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort20GB.log

echo 'start linsort20GB'
time sort /input/data-20GB.in > /tmp/new.txt
