import sys
a = eval(sys.argv[2].replace('/', '//'))
if a == 67:
    a+=2
print(sys.argv[1] + '\tret i32 ' + str(a) + '\n}')
