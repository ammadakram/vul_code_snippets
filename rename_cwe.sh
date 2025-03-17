#!/bin/bash

for dir in CWE-*; do
    if [[ -d "$dir" ]]; then
        cwe_name=$(echo "$dir" | sed 's/-/_/g')  # Convert CWE-XX to CWE_XX
        count=1

        for file in "$dir"/*; do
            if [[ -f "$file" ]]; then
                ext="${file##*.}"  # Extract file extension
                new_name=$(printf "%02d_%s.%s" "$count" "$cwe_name" "$ext")
                mv "$file" "$dir/$new_name"
                ((count++))
            fi
        done
    fi
done

