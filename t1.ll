declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare i32 @getarray(i32*)
declare void @putarray(i32, i32*)
define dso_local i32 @main() {
	%x10000 = alloca i32
	%x10001 = add i32 0, 0
	%x10002 = add i32 0, 0
	%x10003 = icmp eq i32 %x10001, %x10002
	br i1 %x10003, label %l1, label %l2

l1:
	%x10004 = add i32 0, 1
	store i32 %x10004, i32* %x10000
	br label %l3

l2:
	%x10005 = add i32 0, 2
	store i32 %x10005, i32* %x10000
	br label %l3

l3:
	%x10006 = load i32, i32* %x10000
	call void @putint(i32 %x10006)
	%x10007 = add i32 0, 0
	ret i32 %x10007
}
