declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare i32 @getarray(i32*)
declare void @putarray(i32, i32*)
define dso_local i32 @f() {
	%x0 = add i32 0, 0
	call void @putint(i32 %x0)
	%x1 = add i32 0, 0
	ret i32 %x1
}

define dso_local i32 @g() {
	%x0 = add i32 0, 1
	call void @putint(i32 %x0)
	%x1 = add i32 0, 1
	ret i32 %x1
}

define dso_local i32 @main() {
	%x10000 = call i32 @f()
	%x10001 = icmp ne i32 0, %x10000
	br i1 %x10001, label %l5, label %l4

l5:
	%x10002 = call i32 @g()
	%x10003 = icmp ne i32 0, %x10002
	br i1 %x10003, label %l1, label %l4

l4:
	%x10004 = call i32 @f()
	%x10005 = icmp eq i32 0, %x10004
	%x10006 = zext i1 %x10005 to i32
	%x10007 = icmp ne i32 0, %x10006
	br i1 %x10007, label %l6, label %l2

l6:
	%x10008 = call i32 @g()
	%x10009 = icmp ne i32 0, %x10008
	br i1 %x10009, label %l1, label %l2

l1:
	%x10010 = add i32 0, 0
	ret i32 %x10010
	br label %l3

l2:
	%x10011 = add i32 0, 1
	ret i32 %x10011
	br label %l3

l3:
	ret i32 0
}
