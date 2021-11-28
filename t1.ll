declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare i32 @getarray(i32*)
declare void @putarray(i32, i32*)
@g1 = dso_local global [2 x [2 x [3 x i32]]] zeroinitializer
define dso_local i32 @main() {
	%x1 = getelementptr [2 x [2 x [3 x i32]]],[2 x [2 x [3 x i32]]]* @g1, i32 0, i32 0
	%x2 = getelementptr [2 x [3 x i32]],[2 x [3 x i32]]* %x1, i32 0, i32 0
	%x3 = getelementptr [3 x i32],[3 x i32]* %x2, i32 0, i32 0
	%x4 = getelementptr i32,i32* %x3, i32 0
	store i32 1, i32* %x4
	%x5 = getelementptr i32,i32* %x3, i32 1
	store i32 2, i32* %x5
	%x6 = getelementptr i32,i32* %x3, i32 2
	store i32 3, i32* %x6
	%x7 = getelementptr i32,i32* %x3, i32 3
	store i32 0, i32* %x7
	%x8 = getelementptr i32,i32* %x3, i32 4
	store i32 0, i32* %x8
	%x9 = getelementptr i32,i32* %x3, i32 5
	store i32 0, i32* %x9
	%x10 = getelementptr i32,i32* %x3, i32 6
	store i32 0, i32* %x10
	%x11 = getelementptr i32,i32* %x3, i32 7
	store i32 0, i32* %x11
	%x12 = getelementptr i32,i32* %x3, i32 8
	store i32 0, i32* %x12
	%x13 = getelementptr i32,i32* %x3, i32 9
	store i32 0, i32* %x13
	%x14 = getelementptr i32,i32* %x3, i32 10
	store i32 0, i32* %x14
	%x15 = getelementptr i32,i32* %x3, i32 11
	store i32 0, i32* %x15
	%x16 = add i32 0, 5
	%x17 = getelementptr [2 x [2 x [3 x i32]]],[2 x [2 x [3 x i32]]]* @g1, i32 0, i32 0
	%x18 = getelementptr [2 x [3 x i32]],[2 x [3 x i32]]* %x17, i32 0, i32 0
	%x19 = getelementptr [3 x i32],[3 x i32]* %x18, i32 0, i32 0
	%x20 = add i32 0, 0
	%x21 = add i32 0, 0
	%x22 = mul i32 %x21, 1
	%x23 = add i32 %x20, %x22
	%x24 = add i32 0, 0
	%x25 = mul i32 %x24, 3
	%x26 = add i32 %x23, %x25
	%x27 = add i32 0, 0
	%x28 = mul i32 %x27, 6
	%x29 = add i32 %x26, %x28
	%x30 = getelementptr i32,i32* %x19, i32 %x29
	store i32 %x16, i32* %x30
	%x31 = getelementptr [2 x [2 x [3 x i32]]],[2 x [2 x [3 x i32]]]* @g1, i32 0, i32 0
	%x32 = getelementptr [2 x [3 x i32]],[2 x [3 x i32]]* %x31, i32 0, i32 0
	%x33 = getelementptr [3 x i32],[3 x i32]* %x32, i32 0, i32 0
	%x34 = add i32 0, 0
	%x35 = add i32 0, 0
	%x36 = mul i32 %x35, 1
	%x37 = add i32 %x34, %x36
	%x38 = add i32 0, 0
	%x39 = mul i32 %x38, 3
	%x40 = add i32 %x37, %x39
	%x41 = add i32 0, 0
	%x42 = mul i32 %x41, 6
	%x43 = add i32 %x40, %x42
	%x44 = getelementptr i32,i32* %x33, i32 %x43
	%x45 = load i32, i32* %x44
	call void @putint(i32 %x45)
	%x46 = add i32 0, 0
	ret i32 %x46
}
