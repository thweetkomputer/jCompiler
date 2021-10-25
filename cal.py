import sys
print(sys.argv[1] + '\tret i32 ' + str(eval(sys.argv[2].replace('/', '//'))) + '\n}')
