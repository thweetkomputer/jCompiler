declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare i32 @getarray(i32*)
declare void @putarray(i32, i32*)
@g1 = dso_local global [10000 x i32] zeroinitializer
@g2 = dso_local global i32 0
@g3 = dso_local global [10000 x i32] zeroinitializer
@g4 = dso_local global i32 0
@g5 = dso_local global i32 0
@g6 = dso_local global i32 1
@g7 = dso_local global i32 0
@g8 = dso_local global [10000 x i32] zeroinitializer
@g9 = dso_local global [10000 x i32] zeroinitializer
define dso_local i32 @isdigit(i32 %x0) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x2 = load i32, i32* %x1
	%x3 = add i32 0, 48
	%x4 = icmp sge i32 %x2, %x3
	%x5 = load i32, i32* %x1
	%x6 = add i32 0, 57
	%x7 = icmp sle i32 %x5, %x6
	%x8 = and i1 %x4, %x7
	br i1 %x8, label %l1, label %l2

l1:
	%x9 = add i32 0, 1
	ret i32 %x9
	br label %l2

l2:
	%x10 = add i32 0, 0
	ret i32 %x10
}

define dso_local i32 @power(i32 %x0, i32 %x2) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x3 = alloca i32
	store i32 %x2, i32* %x3
	%x4 = alloca i32
	%x5 = add i32 0, 1
	store i32 %x5, i32* %x4
	br label %l3

l3:
	%x6 = load i32, i32* %x3
	%x7 = add i32 0, 0
	%x8 = icmp ne i32 %x6, %x7
	br i1 %x8, label %l4, label %l5

l4:
	%x9 = load i32, i32* %x4
	%x10 = load i32, i32* %x1
	%x11 = mul i32 %x9, %x10
	store i32 %x11, i32* %x4
	%x12 = load i32, i32* %x3
	%x13 = add i32 0, 1
	%x14 = sub i32 %x12, %x13
	store i32 %x14, i32* %x3
	br label %l3

l5:
	%x15 = load i32, i32* %x4
	ret i32 %x15
}

define dso_local i32 @getstr(i32* %x0) {
	%x1 = alloca i32
	%x2 = call i32 @getch()
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 0
	store i32 %x4, i32* %x3
	br label %l6

l6:
	%x5 = load i32, i32* %x1
	%x6 = add i32 0, 13
	%x7 = icmp ne i32 %x5, %x6
	%x8 = load i32, i32* %x1
	%x9 = add i32 0, 10
	%x10 = icmp ne i32 %x8, %x9
	%x11 = and i1 %x7, %x10
	br i1 %x11, label %l7, label %l8

l7:
	%x12 = load i32, i32* %x1
	%x13 = add i32 0, 0
	%x14 = load i32, i32* %x3
	%x15 = mul i32 %x14, 1
	%x16 = add i32 %x13, %x15
	%x17 = getelementptr i32,i32* %x0, i32 %x16
	store i32 %x12, i32* %x17
	%x18 = load i32, i32* %x3
	%x19 = add i32 0, 1
	%x20 = add i32 %x18, %x19
	store i32 %x20, i32* %x3
	%x21 = call i32 @getch()
	store i32 %x21, i32* %x1
	br label %l6

l8:
	%x22 = load i32, i32* %x3
	ret i32 %x22
}

define dso_local void @intpush(i32 %x0) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x2 = load i32, i32* @g2
	%x3 = add i32 0, 1
	%x4 = add i32 %x2, %x3
	store i32 %x4, i32* @g2
	%x5 = load i32, i32* %x1
	%x6 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x7 = add i32 0, 0
	%x8 = load i32, i32* @g2
	%x9 = mul i32 %x8, 1
	%x10 = add i32 %x7, %x9
	%x11 = getelementptr i32,i32* %x6, i32 %x10
	store i32 %x5, i32* %x11
	ret void
}

define dso_local void @chapush(i32 %x0) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x2 = load i32, i32* @g4
	%x3 = add i32 0, 1
	%x4 = add i32 %x2, %x3
	store i32 %x4, i32* @g4
	%x5 = load i32, i32* %x1
	%x6 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x7 = add i32 0, 0
	%x8 = load i32, i32* @g4
	%x9 = mul i32 %x8, 1
	%x10 = add i32 %x7, %x9
	%x11 = getelementptr i32,i32* %x6, i32 %x10
	store i32 %x5, i32* %x11
	ret void
}

define dso_local i32 @intpop() {
	%x0 = load i32, i32* @g2
	%x1 = add i32 0, 1
	%x2 = sub i32 %x0, %x1
	store i32 %x2, i32* @g2
	%x3 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x4 = add i32 0, 0
	%x5 = load i32, i32* @g2
	%x6 = add i32 0, 1
	%x7 = add i32 %x5, %x6
	%x8 = mul i32 %x7, 1
	%x9 = add i32 %x4, %x8
	%x10 = getelementptr i32,i32* %x3, i32 %x9
	%x11 = load i32, i32* %x10
	ret i32 %x11
}

define dso_local i32 @chapop() {
	%x0 = load i32, i32* @g4
	%x1 = add i32 0, 1
	%x2 = sub i32 %x0, %x1
	store i32 %x2, i32* @g4
	%x3 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x4 = add i32 0, 0
	%x5 = load i32, i32* @g4
	%x6 = add i32 0, 1
	%x7 = add i32 %x5, %x6
	%x8 = mul i32 %x7, 1
	%x9 = add i32 %x4, %x8
	%x10 = getelementptr i32,i32* %x3, i32 %x9
	%x11 = load i32, i32* %x10
	ret i32 %x11
}

define dso_local void @intadd(i32 %x0) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x2 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x3 = add i32 0, 0
	%x4 = load i32, i32* @g2
	%x5 = mul i32 %x4, 1
	%x6 = add i32 %x3, %x5
	%x7 = getelementptr i32,i32* %x2, i32 %x6
	%x8 = load i32, i32* %x7
	%x9 = add i32 0, 10
	%x10 = mul i32 %x8, %x9
	%x11 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x12 = add i32 0, 0
	%x13 = load i32, i32* @g2
	%x14 = mul i32 %x13, 1
	%x15 = add i32 %x12, %x14
	%x16 = getelementptr i32,i32* %x11, i32 %x15
	store i32 %x10, i32* %x16
	%x17 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x18 = add i32 0, 0
	%x19 = load i32, i32* @g2
	%x20 = mul i32 %x19, 1
	%x21 = add i32 %x18, %x20
	%x22 = getelementptr i32,i32* %x17, i32 %x21
	%x23 = load i32, i32* %x22
	%x24 = load i32, i32* %x1
	%x25 = add i32 %x23, %x24
	%x26 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x27 = add i32 0, 0
	%x28 = load i32, i32* @g2
	%x29 = mul i32 %x28, 1
	%x30 = add i32 %x27, %x29
	%x31 = getelementptr i32,i32* %x26, i32 %x30
	store i32 %x25, i32* %x31
	ret void
}

define dso_local i32 @find() {
	%x0 = call i32 @chapop()
	store i32 %x0, i32* @g7
	%x1 = add i32 0, 32
	%x2 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x3 = add i32 0, 0
	%x4 = load i32, i32* @g6
	%x5 = mul i32 %x4, 1
	%x6 = add i32 %x3, %x5
	%x7 = getelementptr i32,i32* %x2, i32 %x6
	store i32 %x1, i32* %x7
	%x8 = load i32, i32* @g7
	%x9 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x10 = add i32 0, 0
	%x11 = load i32, i32* @g6
	%x12 = add i32 0, 1
	%x13 = add i32 %x11, %x12
	%x14 = mul i32 %x13, 1
	%x15 = add i32 %x10, %x14
	%x16 = getelementptr i32,i32* %x9, i32 %x15
	store i32 %x8, i32* %x16
	%x17 = load i32, i32* @g6
	%x18 = add i32 0, 2
	%x19 = add i32 %x17, %x18
	store i32 %x19, i32* @g6
	%x20 = load i32, i32* @g4
	%x21 = add i32 0, 0
	%x22 = icmp eq i32 %x20, %x21
	br i1 %x22, label %l9, label %l10

l9:
	%x23 = add i32 0, 0
	ret i32 %x23
	br label %l10

l10:
	%x24 = add i32 0, 1
	ret i32 %x24
}

define dso_local i32 @main() {
	%x0 = add i32 0, 0
	store i32 %x0, i32* @g2
	%x1 = add i32 0, 0
	store i32 %x1, i32* @g4
	%x2 = alloca i32
	%x3 = add i32 0, 0
	%x4 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 %x3
	%x5 = call i32 @getstr(i32* %x4)
	store i32 %x5, i32* %x2
	br label %l11

l11:
	%x6 = load i32, i32* @g5
	%x7 = load i32, i32* %x2
	%x8 = icmp slt i32 %x6, %x7
	br i1 %x8, label %l12, label %l13

l12:
	%x9 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x10 = add i32 0, 0
	%x11 = load i32, i32* @g5
	%x12 = mul i32 %x11, 1
	%x13 = add i32 %x10, %x12
	%x14 = getelementptr i32,i32* %x9, i32 %x13
	%x15 = load i32, i32* %x14
	%x16 = call i32 @isdigit(i32 %x15)
	%x17 = add i32 0, 1
	%x18 = icmp eq i32 %x16, %x17
	br i1 %x18, label %l14, label %l15

l14:
	%x19 = add i32 0, 0
	call void @putint(i32 %x19)
	%x20 = add i32 0, 10
	call void @putch(i32 %x20)
	%x21 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x22 = add i32 0, 0
	%x23 = load i32, i32* @g5
	%x24 = mul i32 %x23, 1
	%x25 = add i32 %x22, %x24
	%x26 = getelementptr i32,i32* %x21, i32 %x25
	%x27 = load i32, i32* %x26
	%x28 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x29 = add i32 0, 0
	%x30 = load i32, i32* @g6
	%x31 = mul i32 %x30, 1
	%x32 = add i32 %x29, %x31
	%x33 = getelementptr i32,i32* %x28, i32 %x32
	store i32 %x27, i32* %x33
	%x34 = load i32, i32* @g6
	%x35 = add i32 0, 1
	%x36 = add i32 %x34, %x35
	store i32 %x36, i32* @g6
	br label %l16

l15:
	%x37 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x38 = add i32 0, 0
	%x39 = load i32, i32* @g5
	%x40 = mul i32 %x39, 1
	%x41 = add i32 %x38, %x40
	%x42 = getelementptr i32,i32* %x37, i32 %x41
	%x43 = load i32, i32* %x42
	%x44 = add i32 0, 40
	%x45 = icmp eq i32 %x43, %x44
	br i1 %x45, label %l17, label %l18

l17:
	%x46 = add i32 0, 40
	call void @chapush(i32 %x46)
	br label %l18

l18:
	%x47 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x48 = add i32 0, 0
	%x49 = load i32, i32* @g5
	%x50 = mul i32 %x49, 1
	%x51 = add i32 %x48, %x50
	%x52 = getelementptr i32,i32* %x47, i32 %x51
	%x53 = load i32, i32* %x52
	%x54 = add i32 0, 94
	%x55 = icmp eq i32 %x53, %x54
	br i1 %x55, label %l19, label %l20

l19:
	%x56 = add i32 0, 94
	call void @chapush(i32 %x56)
	br label %l20

l20:
	%x57 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x58 = add i32 0, 0
	%x59 = load i32, i32* @g5
	%x60 = mul i32 %x59, 1
	%x61 = add i32 %x58, %x60
	%x62 = getelementptr i32,i32* %x57, i32 %x61
	%x63 = load i32, i32* %x62
	%x64 = add i32 0, 41
	%x65 = icmp eq i32 %x63, %x64
	br i1 %x65, label %l21, label %l22

l21:
	%x66 = call i32 @chapop()
	store i32 %x66, i32* @g7
	br label %l23

l23:
	%x67 = load i32, i32* @g7
	%x68 = add i32 0, 40
	%x69 = icmp ne i32 %x67, %x68
	br i1 %x69, label %l24, label %l25

l24:
	%x70 = add i32 0, 32
	%x71 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x72 = add i32 0, 0
	%x73 = load i32, i32* @g6
	%x74 = mul i32 %x73, 1
	%x75 = add i32 %x72, %x74
	%x76 = getelementptr i32,i32* %x71, i32 %x75
	store i32 %x70, i32* %x76
	%x77 = load i32, i32* @g7
	%x78 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x79 = add i32 0, 0
	%x80 = load i32, i32* @g6
	%x81 = add i32 0, 1
	%x82 = add i32 %x80, %x81
	%x83 = mul i32 %x82, 1
	%x84 = add i32 %x79, %x83
	%x85 = getelementptr i32,i32* %x78, i32 %x84
	store i32 %x77, i32* %x85
	%x86 = load i32, i32* @g6
	%x87 = add i32 0, 2
	%x88 = add i32 %x86, %x87
	store i32 %x88, i32* @g6
	%x89 = call i32 @chapop()
	store i32 %x89, i32* @g7
	br label %l23

l25:
	br label %l22

l22:
	%x90 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x91 = add i32 0, 0
	%x92 = load i32, i32* @g5
	%x93 = mul i32 %x92, 1
	%x94 = add i32 %x91, %x93
	%x95 = getelementptr i32,i32* %x90, i32 %x94
	%x96 = load i32, i32* %x95
	%x97 = add i32 0, 43
	%x98 = icmp eq i32 %x96, %x97
	br i1 %x98, label %l26, label %l27

l26:
	br label %l28

l28:
	%x99 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x100 = add i32 0, 0
	%x101 = load i32, i32* @g4
	%x102 = mul i32 %x101, 1
	%x103 = add i32 %x100, %x102
	%x104 = getelementptr i32,i32* %x99, i32 %x103
	%x105 = load i32, i32* %x104
	%x106 = add i32 0, 43
	%x107 = icmp eq i32 %x105, %x106
	%x108 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x109 = add i32 0, 0
	%x110 = load i32, i32* @g4
	%x111 = mul i32 %x110, 1
	%x112 = add i32 %x109, %x111
	%x113 = getelementptr i32,i32* %x108, i32 %x112
	%x114 = load i32, i32* %x113
	%x115 = add i32 0, 45
	%x116 = icmp eq i32 %x114, %x115
	%x117 = or i1 %x107, %x116
	%x118 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x119 = add i32 0, 0
	%x120 = load i32, i32* @g4
	%x121 = mul i32 %x120, 1
	%x122 = add i32 %x119, %x121
	%x123 = getelementptr i32,i32* %x118, i32 %x122
	%x124 = load i32, i32* %x123
	%x125 = add i32 0, 42
	%x126 = icmp eq i32 %x124, %x125
	%x127 = or i1 %x117, %x126
	%x128 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x129 = add i32 0, 0
	%x130 = load i32, i32* @g4
	%x131 = mul i32 %x130, 1
	%x132 = add i32 %x129, %x131
	%x133 = getelementptr i32,i32* %x128, i32 %x132
	%x134 = load i32, i32* %x133
	%x135 = add i32 0, 47
	%x136 = icmp eq i32 %x134, %x135
	%x137 = or i1 %x127, %x136
	%x138 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x139 = add i32 0, 0
	%x140 = load i32, i32* @g4
	%x141 = mul i32 %x140, 1
	%x142 = add i32 %x139, %x141
	%x143 = getelementptr i32,i32* %x138, i32 %x142
	%x144 = load i32, i32* %x143
	%x145 = add i32 0, 37
	%x146 = icmp eq i32 %x144, %x145
	%x147 = or i1 %x137, %x146
	%x148 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x149 = add i32 0, 0
	%x150 = load i32, i32* @g4
	%x151 = mul i32 %x150, 1
	%x152 = add i32 %x149, %x151
	%x153 = getelementptr i32,i32* %x148, i32 %x152
	%x154 = load i32, i32* %x153
	%x155 = add i32 0, 94
	%x156 = icmp eq i32 %x154, %x155
	%x157 = or i1 %x147, %x156
	br i1 %x157, label %l29, label %l30

l29:
	%x158 = call i32 @find()
	%x159 = add i32 0, 0
	%x160 = icmp eq i32 %x158, %x159
	br i1 %x160, label %l31, label %l32

l31:
	br label %l30
	br label %l32

l32:
	br label %l28

l30:
	%x161 = add i32 0, 43
	call void @chapush(i32 %x161)
	br label %l27

l27:
	%x162 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x163 = add i32 0, 0
	%x164 = load i32, i32* @g5
	%x165 = mul i32 %x164, 1
	%x166 = add i32 %x163, %x165
	%x167 = getelementptr i32,i32* %x162, i32 %x166
	%x168 = load i32, i32* %x167
	%x169 = add i32 0, 45
	%x170 = icmp eq i32 %x168, %x169
	br i1 %x170, label %l33, label %l34

l33:
	br label %l35

l35:
	%x171 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x172 = add i32 0, 0
	%x173 = load i32, i32* @g4
	%x174 = mul i32 %x173, 1
	%x175 = add i32 %x172, %x174
	%x176 = getelementptr i32,i32* %x171, i32 %x175
	%x177 = load i32, i32* %x176
	%x178 = add i32 0, 43
	%x179 = icmp eq i32 %x177, %x178
	%x180 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x181 = add i32 0, 0
	%x182 = load i32, i32* @g4
	%x183 = mul i32 %x182, 1
	%x184 = add i32 %x181, %x183
	%x185 = getelementptr i32,i32* %x180, i32 %x184
	%x186 = load i32, i32* %x185
	%x187 = add i32 0, 45
	%x188 = icmp eq i32 %x186, %x187
	%x189 = or i1 %x179, %x188
	%x190 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x191 = add i32 0, 0
	%x192 = load i32, i32* @g4
	%x193 = mul i32 %x192, 1
	%x194 = add i32 %x191, %x193
	%x195 = getelementptr i32,i32* %x190, i32 %x194
	%x196 = load i32, i32* %x195
	%x197 = add i32 0, 42
	%x198 = icmp eq i32 %x196, %x197
	%x199 = or i1 %x189, %x198
	%x200 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x201 = add i32 0, 0
	%x202 = load i32, i32* @g4
	%x203 = mul i32 %x202, 1
	%x204 = add i32 %x201, %x203
	%x205 = getelementptr i32,i32* %x200, i32 %x204
	%x206 = load i32, i32* %x205
	%x207 = add i32 0, 47
	%x208 = icmp eq i32 %x206, %x207
	%x209 = or i1 %x199, %x208
	%x210 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x211 = add i32 0, 0
	%x212 = load i32, i32* @g4
	%x213 = mul i32 %x212, 1
	%x214 = add i32 %x211, %x213
	%x215 = getelementptr i32,i32* %x210, i32 %x214
	%x216 = load i32, i32* %x215
	%x217 = add i32 0, 37
	%x218 = icmp eq i32 %x216, %x217
	%x219 = or i1 %x209, %x218
	%x220 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x221 = add i32 0, 0
	%x222 = load i32, i32* @g4
	%x223 = mul i32 %x222, 1
	%x224 = add i32 %x221, %x223
	%x225 = getelementptr i32,i32* %x220, i32 %x224
	%x226 = load i32, i32* %x225
	%x227 = add i32 0, 94
	%x228 = icmp eq i32 %x226, %x227
	%x229 = or i1 %x219, %x228
	br i1 %x229, label %l36, label %l37

l36:
	%x230 = call i32 @find()
	%x231 = add i32 0, 0
	%x232 = icmp eq i32 %x230, %x231
	br i1 %x232, label %l38, label %l39

l38:
	br label %l37
	br label %l39

l39:
	br label %l35

l37:
	%x233 = add i32 0, 45
	call void @chapush(i32 %x233)
	br label %l34

l34:
	%x234 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x235 = add i32 0, 0
	%x236 = load i32, i32* @g5
	%x237 = mul i32 %x236, 1
	%x238 = add i32 %x235, %x237
	%x239 = getelementptr i32,i32* %x234, i32 %x238
	%x240 = load i32, i32* %x239
	%x241 = add i32 0, 42
	%x242 = icmp eq i32 %x240, %x241
	br i1 %x242, label %l40, label %l41

l40:
	br label %l42

l42:
	%x243 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x244 = add i32 0, 0
	%x245 = load i32, i32* @g4
	%x246 = mul i32 %x245, 1
	%x247 = add i32 %x244, %x246
	%x248 = getelementptr i32,i32* %x243, i32 %x247
	%x249 = load i32, i32* %x248
	%x250 = add i32 0, 42
	%x251 = icmp eq i32 %x249, %x250
	%x252 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x253 = add i32 0, 0
	%x254 = load i32, i32* @g4
	%x255 = mul i32 %x254, 1
	%x256 = add i32 %x253, %x255
	%x257 = getelementptr i32,i32* %x252, i32 %x256
	%x258 = load i32, i32* %x257
	%x259 = add i32 0, 47
	%x260 = icmp eq i32 %x258, %x259
	%x261 = or i1 %x251, %x260
	%x262 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x263 = add i32 0, 0
	%x264 = load i32, i32* @g4
	%x265 = mul i32 %x264, 1
	%x266 = add i32 %x263, %x265
	%x267 = getelementptr i32,i32* %x262, i32 %x266
	%x268 = load i32, i32* %x267
	%x269 = add i32 0, 37
	%x270 = icmp eq i32 %x268, %x269
	%x271 = or i1 %x261, %x270
	%x272 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x273 = add i32 0, 0
	%x274 = load i32, i32* @g4
	%x275 = mul i32 %x274, 1
	%x276 = add i32 %x273, %x275
	%x277 = getelementptr i32,i32* %x272, i32 %x276
	%x278 = load i32, i32* %x277
	%x279 = add i32 0, 94
	%x280 = icmp eq i32 %x278, %x279
	%x281 = or i1 %x271, %x280
	br i1 %x281, label %l43, label %l44

l43:
	%x282 = call i32 @find()
	%x283 = add i32 0, 0
	%x284 = icmp eq i32 %x282, %x283
	br i1 %x284, label %l45, label %l46

l45:
	br label %l44
	br label %l46

l46:
	br label %l42

l44:
	%x285 = add i32 0, 42
	call void @chapush(i32 %x285)
	br label %l41

l41:
	%x286 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x287 = add i32 0, 0
	%x288 = load i32, i32* @g5
	%x289 = mul i32 %x288, 1
	%x290 = add i32 %x287, %x289
	%x291 = getelementptr i32,i32* %x286, i32 %x290
	%x292 = load i32, i32* %x291
	%x293 = add i32 0, 47
	%x294 = icmp eq i32 %x292, %x293
	br i1 %x294, label %l47, label %l48

l47:
	br label %l49

l49:
	%x295 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x296 = add i32 0, 0
	%x297 = load i32, i32* @g4
	%x298 = mul i32 %x297, 1
	%x299 = add i32 %x296, %x298
	%x300 = getelementptr i32,i32* %x295, i32 %x299
	%x301 = load i32, i32* %x300
	%x302 = add i32 0, 42
	%x303 = icmp eq i32 %x301, %x302
	%x304 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x305 = add i32 0, 0
	%x306 = load i32, i32* @g4
	%x307 = mul i32 %x306, 1
	%x308 = add i32 %x305, %x307
	%x309 = getelementptr i32,i32* %x304, i32 %x308
	%x310 = load i32, i32* %x309
	%x311 = add i32 0, 47
	%x312 = icmp eq i32 %x310, %x311
	%x313 = or i1 %x303, %x312
	%x314 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x315 = add i32 0, 0
	%x316 = load i32, i32* @g4
	%x317 = mul i32 %x316, 1
	%x318 = add i32 %x315, %x317
	%x319 = getelementptr i32,i32* %x314, i32 %x318
	%x320 = load i32, i32* %x319
	%x321 = add i32 0, 37
	%x322 = icmp eq i32 %x320, %x321
	%x323 = or i1 %x313, %x322
	%x324 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x325 = add i32 0, 0
	%x326 = load i32, i32* @g4
	%x327 = mul i32 %x326, 1
	%x328 = add i32 %x325, %x327
	%x329 = getelementptr i32,i32* %x324, i32 %x328
	%x330 = load i32, i32* %x329
	%x331 = add i32 0, 94
	%x332 = icmp eq i32 %x330, %x331
	%x333 = or i1 %x323, %x332
	br i1 %x333, label %l50, label %l51

l50:
	%x334 = call i32 @find()
	%x335 = add i32 0, 0
	%x336 = icmp eq i32 %x334, %x335
	br i1 %x336, label %l52, label %l53

l52:
	br label %l51
	br label %l53

l53:
	br label %l49

l51:
	%x337 = add i32 0, 47
	call void @chapush(i32 %x337)
	br label %l48

l48:
	%x338 = getelementptr [10000 x i32],[10000 x i32]* @g8, i32 0, i32 0
	%x339 = add i32 0, 0
	%x340 = load i32, i32* @g5
	%x341 = mul i32 %x340, 1
	%x342 = add i32 %x339, %x341
	%x343 = getelementptr i32,i32* %x338, i32 %x342
	%x344 = load i32, i32* %x343
	%x345 = add i32 0, 37
	%x346 = icmp eq i32 %x344, %x345
	br i1 %x346, label %l54, label %l55

l54:
	br label %l56

l56:
	%x347 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x348 = add i32 0, 0
	%x349 = load i32, i32* @g4
	%x350 = mul i32 %x349, 1
	%x351 = add i32 %x348, %x350
	%x352 = getelementptr i32,i32* %x347, i32 %x351
	%x353 = load i32, i32* %x352
	%x354 = add i32 0, 42
	%x355 = icmp eq i32 %x353, %x354
	%x356 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x357 = add i32 0, 0
	%x358 = load i32, i32* @g4
	%x359 = mul i32 %x358, 1
	%x360 = add i32 %x357, %x359
	%x361 = getelementptr i32,i32* %x356, i32 %x360
	%x362 = load i32, i32* %x361
	%x363 = add i32 0, 47
	%x364 = icmp eq i32 %x362, %x363
	%x365 = or i1 %x355, %x364
	%x366 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x367 = add i32 0, 0
	%x368 = load i32, i32* @g4
	%x369 = mul i32 %x368, 1
	%x370 = add i32 %x367, %x369
	%x371 = getelementptr i32,i32* %x366, i32 %x370
	%x372 = load i32, i32* %x371
	%x373 = add i32 0, 37
	%x374 = icmp eq i32 %x372, %x373
	%x375 = or i1 %x365, %x374
	%x376 = getelementptr [10000 x i32],[10000 x i32]* @g3, i32 0, i32 0
	%x377 = add i32 0, 0
	%x378 = load i32, i32* @g4
	%x379 = mul i32 %x378, 1
	%x380 = add i32 %x377, %x379
	%x381 = getelementptr i32,i32* %x376, i32 %x380
	%x382 = load i32, i32* %x381
	%x383 = add i32 0, 94
	%x384 = icmp eq i32 %x382, %x383
	%x385 = or i1 %x375, %x384
	br i1 %x385, label %l57, label %l58

l57:
	%x386 = call i32 @find()
	%x387 = add i32 0, 0
	%x388 = icmp eq i32 %x386, %x387
	br i1 %x388, label %l59, label %l60

l59:
	br label %l58
	br label %l60

l60:
	br label %l56

l58:
	%x389 = add i32 0, 37
	call void @chapush(i32 %x389)
	br label %l55

l55:
	%x390 = add i32 0, 32
	%x391 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x392 = add i32 0, 0
	%x393 = load i32, i32* @g6
	%x394 = mul i32 %x393, 1
	%x395 = add i32 %x392, %x394
	%x396 = getelementptr i32,i32* %x391, i32 %x395
	store i32 %x390, i32* %x396
	%x397 = load i32, i32* @g6
	%x398 = add i32 0, 1
	%x399 = add i32 %x397, %x398
	store i32 %x399, i32* @g6
	br label %l16

l16:
	%x400 = load i32, i32* @g5
	%x401 = add i32 0, 1
	%x402 = add i32 %x400, %x401
	store i32 %x402, i32* @g5
	br label %l11

l13:
	br label %l61

l61:
	%x403 = load i32, i32* @g4
	%x404 = add i32 0, 0
	%x405 = icmp sgt i32 %x403, %x404
	br i1 %x405, label %l62, label %l63

l62:
	%x406 = alloca i32
	%x407 = call i32 @chapop()
	store i32 %x407, i32* %x406
	%x408 = add i32 0, 32
	%x409 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x410 = add i32 0, 0
	%x411 = load i32, i32* @g6
	%x412 = mul i32 %x411, 1
	%x413 = add i32 %x410, %x412
	%x414 = getelementptr i32,i32* %x409, i32 %x413
	store i32 %x408, i32* %x414
	%x415 = load i32, i32* %x406
	%x416 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x417 = add i32 0, 0
	%x418 = load i32, i32* @g6
	%x419 = add i32 0, 1
	%x420 = add i32 %x418, %x419
	%x421 = mul i32 %x420, 1
	%x422 = add i32 %x417, %x421
	%x423 = getelementptr i32,i32* %x416, i32 %x422
	store i32 %x415, i32* %x423
	%x424 = load i32, i32* @g6
	%x425 = add i32 0, 2
	%x426 = add i32 %x424, %x425
	store i32 %x426, i32* @g6
	br label %l61

l63:
	%x427 = add i32 0, 64
	%x428 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x429 = add i32 0, 0
	%x430 = load i32, i32* @g6
	%x431 = mul i32 %x430, 1
	%x432 = add i32 %x429, %x431
	%x433 = getelementptr i32,i32* %x428, i32 %x432
	store i32 %x427, i32* %x433
	%x434 = add i32 0, 1
	store i32 %x434, i32* @g5
	br label %l64

l64:
	%x435 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x436 = add i32 0, 0
	%x437 = load i32, i32* @g5
	%x438 = mul i32 %x437, 1
	%x439 = add i32 %x436, %x438
	%x440 = getelementptr i32,i32* %x435, i32 %x439
	%x441 = load i32, i32* %x440
	%x442 = add i32 0, 64
	%x443 = icmp ne i32 %x441, %x442
	br i1 %x443, label %l65, label %l66

l65:
	%x444 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x445 = add i32 0, 0
	%x446 = load i32, i32* @g5
	%x447 = mul i32 %x446, 1
	%x448 = add i32 %x445, %x447
	%x449 = getelementptr i32,i32* %x444, i32 %x448
	%x450 = load i32, i32* %x449
	%x451 = add i32 0, 43
	%x452 = icmp eq i32 %x450, %x451
	%x453 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x454 = add i32 0, 0
	%x455 = load i32, i32* @g5
	%x456 = mul i32 %x455, 1
	%x457 = add i32 %x454, %x456
	%x458 = getelementptr i32,i32* %x453, i32 %x457
	%x459 = load i32, i32* %x458
	%x460 = add i32 0, 45
	%x461 = icmp eq i32 %x459, %x460
	%x462 = or i1 %x452, %x461
	%x463 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x464 = add i32 0, 0
	%x465 = load i32, i32* @g5
	%x466 = mul i32 %x465, 1
	%x467 = add i32 %x464, %x466
	%x468 = getelementptr i32,i32* %x463, i32 %x467
	%x469 = load i32, i32* %x468
	%x470 = add i32 0, 42
	%x471 = icmp eq i32 %x469, %x470
	%x472 = or i1 %x462, %x471
	%x473 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x474 = add i32 0, 0
	%x475 = load i32, i32* @g5
	%x476 = mul i32 %x475, 1
	%x477 = add i32 %x474, %x476
	%x478 = getelementptr i32,i32* %x473, i32 %x477
	%x479 = load i32, i32* %x478
	%x480 = add i32 0, 47
	%x481 = icmp eq i32 %x479, %x480
	%x482 = or i1 %x472, %x481
	%x483 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x484 = add i32 0, 0
	%x485 = load i32, i32* @g5
	%x486 = mul i32 %x485, 1
	%x487 = add i32 %x484, %x486
	%x488 = getelementptr i32,i32* %x483, i32 %x487
	%x489 = load i32, i32* %x488
	%x490 = add i32 0, 37
	%x491 = icmp eq i32 %x489, %x490
	%x492 = or i1 %x482, %x491
	%x493 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x494 = add i32 0, 0
	%x495 = load i32, i32* @g5
	%x496 = mul i32 %x495, 1
	%x497 = add i32 %x494, %x496
	%x498 = getelementptr i32,i32* %x493, i32 %x497
	%x499 = load i32, i32* %x498
	%x500 = add i32 0, 94
	%x501 = icmp eq i32 %x499, %x500
	%x502 = or i1 %x492, %x501
	br i1 %x502, label %l67, label %l68

l67:
	%x503 = alloca i32
	%x504 = call i32 @intpop()
	store i32 %x504, i32* %x503
	%x505 = alloca i32
	%x506 = call i32 @intpop()
	store i32 %x506, i32* %x505
	%x507 = alloca i32
	%x508 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x509 = add i32 0, 0
	%x510 = load i32, i32* @g5
	%x511 = mul i32 %x510, 1
	%x512 = add i32 %x509, %x511
	%x513 = getelementptr i32,i32* %x508, i32 %x512
	%x514 = load i32, i32* %x513
	%x515 = add i32 0, 43
	%x516 = icmp eq i32 %x514, %x515
	br i1 %x516, label %l69, label %l70

l69:
	%x517 = load i32, i32* %x503
	%x518 = load i32, i32* %x505
	%x519 = add i32 %x517, %x518
	store i32 %x519, i32* %x507
	br label %l70

l70:
	%x520 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x521 = add i32 0, 0
	%x522 = load i32, i32* @g5
	%x523 = mul i32 %x522, 1
	%x524 = add i32 %x521, %x523
	%x525 = getelementptr i32,i32* %x520, i32 %x524
	%x526 = load i32, i32* %x525
	%x527 = add i32 0, 45
	%x528 = icmp eq i32 %x526, %x527
	br i1 %x528, label %l71, label %l72

l71:
	%x529 = load i32, i32* %x505
	%x530 = load i32, i32* %x503
	%x531 = sub i32 %x529, %x530
	store i32 %x531, i32* %x507
	br label %l72

l72:
	%x532 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x533 = add i32 0, 0
	%x534 = load i32, i32* @g5
	%x535 = mul i32 %x534, 1
	%x536 = add i32 %x533, %x535
	%x537 = getelementptr i32,i32* %x532, i32 %x536
	%x538 = load i32, i32* %x537
	%x539 = add i32 0, 42
	%x540 = icmp eq i32 %x538, %x539
	br i1 %x540, label %l73, label %l74

l73:
	%x541 = load i32, i32* %x503
	%x542 = load i32, i32* %x505
	%x543 = mul i32 %x541, %x542
	store i32 %x543, i32* %x507
	br label %l74

l74:
	%x544 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x545 = add i32 0, 0
	%x546 = load i32, i32* @g5
	%x547 = mul i32 %x546, 1
	%x548 = add i32 %x545, %x547
	%x549 = getelementptr i32,i32* %x544, i32 %x548
	%x550 = load i32, i32* %x549
	%x551 = add i32 0, 47
	%x552 = icmp eq i32 %x550, %x551
	br i1 %x552, label %l75, label %l76

l75:
	%x553 = load i32, i32* %x505
	%x554 = load i32, i32* %x503
	%x555 = sdiv i32 %x553, %x554
	store i32 %x555, i32* %x507
	br label %l76

l76:
	%x556 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x557 = add i32 0, 0
	%x558 = load i32, i32* @g5
	%x559 = mul i32 %x558, 1
	%x560 = add i32 %x557, %x559
	%x561 = getelementptr i32,i32* %x556, i32 %x560
	%x562 = load i32, i32* %x561
	%x563 = add i32 0, 37
	%x564 = icmp eq i32 %x562, %x563
	br i1 %x564, label %l77, label %l78

l77:
	%x565 = load i32, i32* %x505
	%x566 = load i32, i32* %x503
	%x567 = srem i32 %x565, %x566
	store i32 %x567, i32* %x507
	br label %l78

l78:
	%x568 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x569 = add i32 0, 0
	%x570 = load i32, i32* @g5
	%x571 = mul i32 %x570, 1
	%x572 = add i32 %x569, %x571
	%x573 = getelementptr i32,i32* %x568, i32 %x572
	%x574 = load i32, i32* %x573
	%x575 = add i32 0, 94
	%x576 = icmp eq i32 %x574, %x575
	br i1 %x576, label %l79, label %l80

l79:
	%x577 = load i32, i32* %x505
	%x578 = load i32, i32* %x503
	%x579 = call i32 @power(i32 %x577, i32 %x578)
	store i32 %x579, i32* %x507
	br label %l80

l80:
	%x580 = load i32, i32* %x507
	call void @intpush(i32 %x580)
	br label %l81

l68:
	%x581 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x582 = add i32 0, 0
	%x583 = load i32, i32* @g5
	%x584 = mul i32 %x583, 1
	%x585 = add i32 %x582, %x584
	%x586 = getelementptr i32,i32* %x581, i32 %x585
	%x587 = load i32, i32* %x586
	%x588 = add i32 0, 32
	%x589 = icmp ne i32 %x587, %x588
	br i1 %x589, label %l82, label %l83

l82:
	%x590 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x591 = add i32 0, 0
	%x592 = load i32, i32* @g5
	%x593 = mul i32 %x592, 1
	%x594 = add i32 %x591, %x593
	%x595 = getelementptr i32,i32* %x590, i32 %x594
	%x596 = load i32, i32* %x595
	%x597 = add i32 0, 48
	%x598 = sub i32 %x596, %x597
	call void @intpush(i32 %x598)
	%x599 = add i32 0, 1
	store i32 %x599, i32* @g6
	br label %l84

l84:
	%x600 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x601 = add i32 0, 0
	%x602 = load i32, i32* @g5
	%x603 = load i32, i32* @g6
	%x604 = add i32 %x602, %x603
	%x605 = mul i32 %x604, 1
	%x606 = add i32 %x601, %x605
	%x607 = getelementptr i32,i32* %x600, i32 %x606
	%x608 = load i32, i32* %x607
	%x609 = add i32 0, 32
	%x610 = icmp ne i32 %x608, %x609
	br i1 %x610, label %l85, label %l86

l85:
	%x611 = getelementptr [10000 x i32],[10000 x i32]* @g9, i32 0, i32 0
	%x612 = add i32 0, 0
	%x613 = load i32, i32* @g5
	%x614 = load i32, i32* @g6
	%x615 = add i32 %x613, %x614
	%x616 = mul i32 %x615, 1
	%x617 = add i32 %x612, %x616
	%x618 = getelementptr i32,i32* %x611, i32 %x617
	%x619 = load i32, i32* %x618
	%x620 = add i32 0, 48
	%x621 = sub i32 %x619, %x620
	call void @intadd(i32 %x621)
	%x622 = load i32, i32* @g6
	%x623 = add i32 0, 1
	%x624 = add i32 %x622, %x623
	store i32 %x624, i32* @g6
	br label %l84

l86:
	%x625 = load i32, i32* @g5
	%x626 = load i32, i32* @g6
	%x627 = add i32 %x625, %x626
	%x628 = add i32 0, 1
	%x629 = sub i32 %x627, %x628
	store i32 %x629, i32* @g5
	br label %l83

l83:
	br label %l81

l81:
	%x630 = load i32, i32* @g5
	%x631 = add i32 0, 1
	%x632 = add i32 %x630, %x631
	store i32 %x632, i32* @g5
	br label %l64

l66:
	%x633 = getelementptr [10000 x i32],[10000 x i32]* @g1, i32 0, i32 0
	%x634 = add i32 0, 0
	%x635 = add i32 0, 1
	%x636 = mul i32 %x635, 1
	%x637 = add i32 %x634, %x636
	%x638 = getelementptr i32,i32* %x633, i32 %x637
	%x639 = load i32, i32* %x638
	call void @putint(i32 %x639)
	%x640 = add i32 0, 0
	ret i32 %x640
}
