#!/usr/bin/env python
# enable debugging
import cgitb
cgitb.enable()
import datetime

print("Contect-Type: text/html;charset=utf-8")
print("")

def main():
    print("Current date:")
    print(datetime.datetime.now().date())

if __name__ == "__main__":
    main()