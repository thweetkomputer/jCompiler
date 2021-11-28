declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare i32 @getarray(i32*)
declare void @putarray(i32, i32*)
@g1 = dso_local global [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]] zeroinitializer
@g2 = dso_local global [200 x i32] zeroinitializer
define dso_local i32 @equal(i32 %x0, i32 %x2) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x3 = alloca i32
	store i32 %x2, i32* %x3
	%x4 = load i32, i32* %x1
	%x5 = load i32, i32* %x3
	%x6 = icmp eq i32 %x4, %x5
	br i1 %x6, label %l1, label %l2

l1:
	%x7 = add i32 0, 1
	ret i32 %x7
	br label %l2

l2:
	%x8 = add i32 0, 0
	ret i32 %x8
}

define dso_local i32 @dfs(i32 %x0, i32 %x2, i32 %x4, i32 %x6, i32 %x8, i32 %x10) {
	%x1 = alloca i32
	store i32 %x0, i32* %x1
	%x3 = alloca i32
	store i32 %x2, i32* %x3
	%x5 = alloca i32
	store i32 %x4, i32* %x5
	%x7 = alloca i32
	store i32 %x6, i32* %x7
	%x9 = alloca i32
	store i32 %x8, i32* %x9
	%x11 = alloca i32
	store i32 %x10, i32* %x11
	%x12 = getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @g1, i32 0, i32 0
	%x13 = getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]* %x12, i32 0, i32 0
	%x14 = getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]* %x13, i32 0, i32 0
	%x15 = getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]* %x14, i32 0, i32 0
	%x16 = getelementptr [18 x [7 x i32]],[18 x [7 x i32]]* %x15, i32 0, i32 0
	%x17 = getelementptr [7 x i32],[7 x i32]* %x16, i32 0, i32 0
	%x18 = add i32 0, 0
	%x19 = load i32, i32* %x11
	%x20 = mul i32 %x19, 1
	%x21 = add i32 %x18, %x20
	%x22 = load i32, i32* %x9
	%x23 = mul i32 %x22, 7
	%x24 = add i32 %x21, %x23
	%x25 = load i32, i32* %x7
	%x26 = mul i32 %x25, 126
	%x27 = add i32 %x24, %x26
	%x28 = load i32, i32* %x5
	%x29 = mul i32 %x28, 2268
	%x30 = add i32 %x27, %x29
	%x31 = load i32, i32* %x3
	%x32 = mul i32 %x31, 40824
	%x33 = add i32 %x30, %x32
	%x34 = load i32, i32* %x1
	%x35 = mul i32 %x34, 734832
	%x36 = add i32 %x33, %x35
	%x37 = getelementptr i32,i32* %x17, i32 %x36
	%x38 = load i32, i32* %x37
	%x39 = add i32 0, 1
	%x40 = sub i32 0, %x39
	%x41 = icmp ne i32 %x38, %x40
	br i1 %x41, label %l3, label %l4

l3:
	%x42 = getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @g1, i32 0, i32 0
	%x43 = getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]* %x42, i32 0, i32 0
	%x44 = getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]* %x43, i32 0, i32 0
	%x45 = getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]* %x44, i32 0, i32 0
	%x46 = getelementptr [18 x [7 x i32]],[18 x [7 x i32]]* %x45, i32 0, i32 0
	%x47 = getelementptr [7 x i32],[7 x i32]* %x46, i32 0, i32 0
	%x48 = add i32 0, 0
	%x49 = load i32, i32* %x11
	%x50 = mul i32 %x49, 1
	%x51 = add i32 %x48, %x50
	%x52 = load i32, i32* %x9
	%x53 = mul i32 %x52, 7
	%x54 = add i32 %x51, %x53
	%x55 = load i32, i32* %x7
	%x56 = mul i32 %x55, 126
	%x57 = add i32 %x54, %x56
	%x58 = load i32, i32* %x5
	%x59 = mul i32 %x58, 2268
	%x60 = add i32 %x57, %x59
	%x61 = load i32, i32* %x3
	%x62 = mul i32 %x61, 40824
	%x63 = add i32 %x60, %x62
	%x64 = load i32, i32* %x1
	%x65 = mul i32 %x64, 734832
	%x66 = add i32 %x63, %x65
	%x67 = getelementptr i32,i32* %x47, i32 %x66
	%x68 = load i32, i32* %x67
	ret i32 %x68
	br label %l4

l4:
	%x69 = load i32, i32* %x1
	%x70 = load i32, i32* %x3
	%x71 = add i32 %x69, %x70
	%x72 = load i32, i32* %x5
	%x73 = add i32 %x71, %x72
	%x74 = load i32, i32* %x7
	%x75 = add i32 %x73, %x74
	%x76 = load i32, i32* %x9
	%x77 = add i32 %x75, %x76
	%x78 = add i32 0, 0
	%x79 = icmp eq i32 %x77, %x78
	br i1 %x79, label %l5, label %l6

l5:
	%x80 = add i32 0, 1
	ret i32 %x80
	br label %l6

l6:
	%x81 = alloca i32
	%x82 = add i32 0, 0
	store i32 %x82, i32* %x81
	%x83 = load i32, i32* %x1
	%x84 = icmp ne i32 0, %x83
	br i1 %x84, label %l7, label %l8

l7:
	%x85 = load i32, i32* %x81
	%x86 = load i32, i32* %x1
	%x87 = load i32, i32* %x11
	%x88 = add i32 0, 2
	%x89 = call i32 @equal(i32 %x87, i32 %x88)
	%x90 = sub i32 %x86, %x89
	%x91 = load i32, i32* %x1
	%x92 = add i32 0, 1
	%x93 = sub i32 %x91, %x92
	%x94 = load i32, i32* %x3
	%x95 = load i32, i32* %x5
	%x96 = load i32, i32* %x7
	%x97 = load i32, i32* %x9
	%x98 = add i32 0, 1
	%x99 = call i32 @dfs(i32 %x93, i32 %x94, i32 %x95, i32 %x96, i32 %x97, i32 %x98)
	%x100 = mul i32 %x90, %x99
	%x101 = add i32 %x85, %x100
	%x102 = add i32 0, 1000000007
	%x103 = srem i32 %x101, %x102
	store i32 %x103, i32* %x81
	br label %l8

l8:
	%x104 = load i32, i32* %x3
	%x105 = icmp ne i32 0, %x104
	br i1 %x105, label %l9, label %l10

l9:
	%x106 = load i32, i32* %x81
	%x107 = load i32, i32* %x3
	%x108 = load i32, i32* %x11
	%x109 = add i32 0, 3
	%x110 = call i32 @equal(i32 %x108, i32 %x109)
	%x111 = sub i32 %x107, %x110
	%x112 = load i32, i32* %x1
	%x113 = add i32 0, 1
	%x114 = add i32 %x112, %x113
	%x115 = load i32, i32* %x3
	%x116 = add i32 0, 1
	%x117 = sub i32 %x115, %x116
	%x118 = load i32, i32* %x5
	%x119 = load i32, i32* %x7
	%x120 = load i32, i32* %x9
	%x121 = add i32 0, 2
	%x122 = call i32 @dfs(i32 %x114, i32 %x117, i32 %x118, i32 %x119, i32 %x120, i32 %x121)
	%x123 = mul i32 %x111, %x122
	%x124 = add i32 %x106, %x123
	%x125 = add i32 0, 1000000007
	%x126 = srem i32 %x124, %x125
	store i32 %x126, i32* %x81
	br label %l10

l10:
	%x127 = load i32, i32* %x5
	%x128 = icmp ne i32 0, %x127
	br i1 %x128, label %l11, label %l12

l11:
	%x129 = load i32, i32* %x81
	%x130 = load i32, i32* %x5
	%x131 = load i32, i32* %x11
	%x132 = add i32 0, 4
	%x133 = call i32 @equal(i32 %x131, i32 %x132)
	%x134 = sub i32 %x130, %x133
	%x135 = load i32, i32* %x1
	%x136 = load i32, i32* %x3
	%x137 = add i32 0, 1
	%x138 = add i32 %x136, %x137
	%x139 = load i32, i32* %x5
	%x140 = add i32 0, 1
	%x141 = sub i32 %x139, %x140
	%x142 = load i32, i32* %x7
	%x143 = load i32, i32* %x9
	%x144 = add i32 0, 3
	%x145 = call i32 @dfs(i32 %x135, i32 %x138, i32 %x141, i32 %x142, i32 %x143, i32 %x144)
	%x146 = mul i32 %x134, %x145
	%x147 = add i32 %x129, %x146
	%x148 = add i32 0, 1000000007
	%x149 = srem i32 %x147, %x148
	store i32 %x149, i32* %x81
	br label %l12

l12:
	%x150 = load i32, i32* %x7
	%x151 = icmp ne i32 0, %x150
	br i1 %x151, label %l13, label %l14

l13:
	%x152 = load i32, i32* %x81
	%x153 = load i32, i32* %x7
	%x154 = load i32, i32* %x11
	%x155 = add i32 0, 5
	%x156 = call i32 @equal(i32 %x154, i32 %x155)
	%x157 = sub i32 %x153, %x156
	%x158 = load i32, i32* %x1
	%x159 = load i32, i32* %x3
	%x160 = load i32, i32* %x5
	%x161 = add i32 0, 1
	%x162 = add i32 %x160, %x161
	%x163 = load i32, i32* %x7
	%x164 = add i32 0, 1
	%x165 = sub i32 %x163, %x164
	%x166 = load i32, i32* %x9
	%x167 = add i32 0, 4
	%x168 = call i32 @dfs(i32 %x158, i32 %x159, i32 %x162, i32 %x165, i32 %x166, i32 %x167)
	%x169 = mul i32 %x157, %x168
	%x170 = add i32 %x152, %x169
	%x171 = add i32 0, 1000000007
	%x172 = srem i32 %x170, %x171
	store i32 %x172, i32* %x81
	br label %l14

l14:
	%x173 = load i32, i32* %x9
	%x174 = icmp ne i32 0, %x173
	br i1 %x174, label %l15, label %l16

l15:
	%x175 = load i32, i32* %x81
	%x176 = load i32, i32* %x9
	%x177 = load i32, i32* %x1
	%x178 = load i32, i32* %x3
	%x179 = load i32, i32* %x5
	%x180 = load i32, i32* %x7
	%x181 = add i32 0, 1
	%x182 = add i32 %x180, %x181
	%x183 = load i32, i32* %x9
	%x184 = add i32 0, 1
	%x185 = sub i32 %x183, %x184
	%x186 = add i32 0, 5
	%x187 = call i32 @dfs(i32 %x177, i32 %x178, i32 %x179, i32 %x182, i32 %x185, i32 %x186)
	%x188 = mul i32 %x176, %x187
	%x189 = add i32 %x175, %x188
	%x190 = add i32 0, 1000000007
	%x191 = srem i32 %x189, %x190
	store i32 %x191, i32* %x81
	br label %l16

l16:
	%x192 = load i32, i32* %x81
	%x193 = add i32 0, 1000000007
	%x194 = srem i32 %x192, %x193
	%x195 = getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @g1, i32 0, i32 0
	%x196 = getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]* %x195, i32 0, i32 0
	%x197 = getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]* %x196, i32 0, i32 0
	%x198 = getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]* %x197, i32 0, i32 0
	%x199 = getelementptr [18 x [7 x i32]],[18 x [7 x i32]]* %x198, i32 0, i32 0
	%x200 = getelementptr [7 x i32],[7 x i32]* %x199, i32 0, i32 0
	%x201 = add i32 0, 0
	%x202 = load i32, i32* %x11
	%x203 = mul i32 %x202, 1
	%x204 = add i32 %x201, %x203
	%x205 = load i32, i32* %x9
	%x206 = mul i32 %x205, 7
	%x207 = add i32 %x204, %x206
	%x208 = load i32, i32* %x7
	%x209 = mul i32 %x208, 126
	%x210 = add i32 %x207, %x209
	%x211 = load i32, i32* %x5
	%x212 = mul i32 %x211, 2268
	%x213 = add i32 %x210, %x212
	%x214 = load i32, i32* %x3
	%x215 = mul i32 %x214, 40824
	%x216 = add i32 %x213, %x215
	%x217 = load i32, i32* %x1
	%x218 = mul i32 %x217, 734832
	%x219 = add i32 %x216, %x218
	%x220 = getelementptr i32,i32* %x200, i32 %x219
	store i32 %x194, i32* %x220
	%x221 = getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @g1, i32 0, i32 0
	%x222 = getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]* %x221, i32 0, i32 0
	%x223 = getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]* %x222, i32 0, i32 0
	%x224 = getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]* %x223, i32 0, i32 0
	%x225 = getelementptr [18 x [7 x i32]],[18 x [7 x i32]]* %x224, i32 0, i32 0
	%x226 = getelementptr [7 x i32],[7 x i32]* %x225, i32 0, i32 0
	%x227 = add i32 0, 0
	%x228 = load i32, i32* %x11
	%x229 = mul i32 %x228, 1
	%x230 = add i32 %x227, %x229
	%x231 = load i32, i32* %x9
	%x232 = mul i32 %x231, 7
	%x233 = add i32 %x230, %x232
	%x234 = load i32, i32* %x7
	%x235 = mul i32 %x234, 126
	%x236 = add i32 %x233, %x235
	%x237 = load i32, i32* %x5
	%x238 = mul i32 %x237, 2268
	%x239 = add i32 %x236, %x238
	%x240 = load i32, i32* %x3
	%x241 = mul i32 %x240, 40824
	%x242 = add i32 %x239, %x241
	%x243 = load i32, i32* %x1
	%x244 = mul i32 %x243, 734832
	%x245 = add i32 %x242, %x244
	%x246 = getelementptr i32,i32* %x226, i32 %x245
	%x247 = load i32, i32* %x246
	ret i32 %x247
}

@g3 = dso_local global [20 x i32] zeroinitializer
define dso_local i32 @main() {
	%x10000 = alloca i32
	%x10001 = call i32 @getint()
	store i32 %x10001, i32* %x10000
	%x10002 = alloca i32
	%x10003 = add i32 0, 0
	store i32 %x10003, i32* %x10002
	br label %l17

l17:
	%x10004 = load i32, i32* %x10002
	%x10005 = add i32 0, 18
	%x10006 = icmp slt i32 %x10004, %x10005
	br i1 %x10006, label %l18, label %l19

l18:
	%x10007 = alloca i32
	%x10008 = add i32 0, 0
	store i32 %x10008, i32* %x10007
	br label %l20

l20:
	%x10009 = load i32, i32* %x10007
	%x10010 = add i32 0, 18
	%x10011 = icmp slt i32 %x10009, %x10010
	br i1 %x10011, label %l21, label %l22

l21:
	%x10012 = alloca i32
	%x10013 = add i32 0, 0
	store i32 %x10013, i32* %x10012
	br label %l23

l23:
	%x10014 = load i32, i32* %x10012
	%x10015 = add i32 0, 18
	%x10016 = icmp slt i32 %x10014, %x10015
	br i1 %x10016, label %l24, label %l25

l24:
	%x10017 = alloca i32
	%x10018 = add i32 0, 0
	store i32 %x10018, i32* %x10017
	br label %l26

l26:
	%x10019 = load i32, i32* %x10017
	%x10020 = add i32 0, 18
	%x10021 = icmp slt i32 %x10019, %x10020
	br i1 %x10021, label %l27, label %l28

l27:
	%x10022 = alloca i32
	%x10023 = add i32 0, 0
	store i32 %x10023, i32* %x10022
	br label %l29

l29:
	%x10024 = load i32, i32* %x10022
	%x10025 = add i32 0, 18
	%x10026 = icmp slt i32 %x10024, %x10025
	br i1 %x10026, label %l30, label %l31

l30:
	%x10027 = alloca i32
	%x10028 = add i32 0, 0
	store i32 %x10028, i32* %x10027
	br label %l32

l32:
	%x10029 = load i32, i32* %x10027
	%x10030 = add i32 0, 7
	%x10031 = icmp slt i32 %x10029, %x10030
	br i1 %x10031, label %l33, label %l34

l33:
	%x10032 = add i32 0, 1
	%x10033 = sub i32 0, %x10032
	%x10034 = getelementptr [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],[18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @g1, i32 0, i32 0
	%x10035 = getelementptr [18 x [18 x [18 x [18 x [7 x i32]]]]],[18 x [18 x [18 x [18 x [7 x i32]]]]]* %x10034, i32 0, i32 0
	%x10036 = getelementptr [18 x [18 x [18 x [7 x i32]]]],[18 x [18 x [18 x [7 x i32]]]]* %x10035, i32 0, i32 0
	%x10037 = getelementptr [18 x [18 x [7 x i32]]],[18 x [18 x [7 x i32]]]* %x10036, i32 0, i32 0
	%x10038 = getelementptr [18 x [7 x i32]],[18 x [7 x i32]]* %x10037, i32 0, i32 0
	%x10039 = getelementptr [7 x i32],[7 x i32]* %x10038, i32 0, i32 0
	%x10040 = add i32 0, 0
	%x10041 = load i32, i32* %x10027
	%x10042 = mul i32 %x10041, 1
	%x10043 = add i32 %x10040, %x10042
	%x10044 = load i32, i32* %x10022
	%x10045 = mul i32 %x10044, 7
	%x10046 = add i32 %x10043, %x10045
	%x10047 = load i32, i32* %x10017
	%x10048 = mul i32 %x10047, 126
	%x10049 = add i32 %x10046, %x10048
	%x10050 = load i32, i32* %x10012
	%x10051 = mul i32 %x10050, 2268
	%x10052 = add i32 %x10049, %x10051
	%x10053 = load i32, i32* %x10007
	%x10054 = mul i32 %x10053, 40824
	%x10055 = add i32 %x10052, %x10054
	%x10056 = load i32, i32* %x10002
	%x10057 = mul i32 %x10056, 734832
	%x10058 = add i32 %x10055, %x10057
	%x10059 = getelementptr i32,i32* %x10039, i32 %x10058
	store i32 %x10033, i32* %x10059
	%x10060 = load i32, i32* %x10027
	%x10061 = add i32 0, 1
	%x10062 = add i32 %x10060, %x10061
	store i32 %x10062, i32* %x10027
	br label %l32

l34:
	%x10063 = load i32, i32* %x10022
	%x10064 = add i32 0, 1
	%x10065 = add i32 %x10063, %x10064
	store i32 %x10065, i32* %x10022
	br label %l29

l31:
	%x10066 = load i32, i32* %x10017
	%x10067 = add i32 0, 1
	%x10068 = add i32 %x10066, %x10067
	store i32 %x10068, i32* %x10017
	br label %l26

l28:
	%x10069 = load i32, i32* %x10012
	%x10070 = add i32 0, 1
	%x10071 = add i32 %x10069, %x10070
	store i32 %x10071, i32* %x10012
	br label %l23

l25:
	%x10072 = load i32, i32* %x10007
	%x10073 = add i32 0, 1
	%x10074 = add i32 %x10072, %x10073
	store i32 %x10074, i32* %x10007
	br label %l20

l22:
	%x10075 = load i32, i32* %x10002
	%x10076 = add i32 0, 1
	%x10077 = add i32 %x10075, %x10076
	store i32 %x10077, i32* %x10002
	br label %l17

l19:
	%x10078 = add i32 0, 0
	store i32 %x10078, i32* %x10002
	br label %l35

l35:
	%x10079 = load i32, i32* %x10002
	%x10080 = load i32, i32* %x10000
	%x10081 = icmp slt i32 %x10079, %x10080
	br i1 %x10081, label %l36, label %l37

l36:
	%x10082 = call i32 @getint()
	%x10083 = getelementptr [200 x i32],[200 x i32]* @g2, i32 0, i32 0
	%x10084 = add i32 0, 0
	%x10085 = load i32, i32* %x10002
	%x10086 = mul i32 %x10085, 1
	%x10087 = add i32 %x10084, %x10086
	%x10088 = getelementptr i32,i32* %x10083, i32 %x10087
	store i32 %x10082, i32* %x10088
	%x10089 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10090 = add i32 0, 0
	%x10091 = getelementptr [200 x i32],[200 x i32]* @g2, i32 0, i32 0
	%x10092 = add i32 0, 0
	%x10093 = load i32, i32* %x10002
	%x10094 = mul i32 %x10093, 1
	%x10095 = add i32 %x10092, %x10094
	%x10096 = getelementptr i32,i32* %x10091, i32 %x10095
	%x10097 = load i32, i32* %x10096
	%x10098 = mul i32 %x10097, 1
	%x10099 = add i32 %x10090, %x10098
	%x10100 = getelementptr i32,i32* %x10089, i32 %x10099
	%x10101 = load i32, i32* %x10100
	%x10102 = add i32 0, 1
	%x10103 = add i32 %x10101, %x10102
	%x10104 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10105 = add i32 0, 0
	%x10106 = getelementptr [200 x i32],[200 x i32]* @g2, i32 0, i32 0
	%x10107 = add i32 0, 0
	%x10108 = load i32, i32* %x10002
	%x10109 = mul i32 %x10108, 1
	%x10110 = add i32 %x10107, %x10109
	%x10111 = getelementptr i32,i32* %x10106, i32 %x10110
	%x10112 = load i32, i32* %x10111
	%x10113 = mul i32 %x10112, 1
	%x10114 = add i32 %x10105, %x10113
	%x10115 = getelementptr i32,i32* %x10104, i32 %x10114
	store i32 %x10103, i32* %x10115
	%x10116 = load i32, i32* %x10002
	%x10117 = add i32 0, 1
	%x10118 = add i32 %x10116, %x10117
	store i32 %x10118, i32* %x10002
	br label %l35

l37:
	%x10119 = alloca i32
	%x10120 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10121 = add i32 0, 0
	%x10122 = add i32 0, 1
	%x10123 = mul i32 %x10122, 1
	%x10124 = add i32 %x10121, %x10123
	%x10125 = getelementptr i32,i32* %x10120, i32 %x10124
	%x10126 = load i32, i32* %x10125
	%x10127 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10128 = add i32 0, 0
	%x10129 = add i32 0, 2
	%x10130 = mul i32 %x10129, 1
	%x10131 = add i32 %x10128, %x10130
	%x10132 = getelementptr i32,i32* %x10127, i32 %x10131
	%x10133 = load i32, i32* %x10132
	%x10134 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10135 = add i32 0, 0
	%x10136 = add i32 0, 3
	%x10137 = mul i32 %x10136, 1
	%x10138 = add i32 %x10135, %x10137
	%x10139 = getelementptr i32,i32* %x10134, i32 %x10138
	%x10140 = load i32, i32* %x10139
	%x10141 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10142 = add i32 0, 0
	%x10143 = add i32 0, 4
	%x10144 = mul i32 %x10143, 1
	%x10145 = add i32 %x10142, %x10144
	%x10146 = getelementptr i32,i32* %x10141, i32 %x10145
	%x10147 = load i32, i32* %x10146
	%x10148 = getelementptr [20 x i32],[20 x i32]* @g3, i32 0, i32 0
	%x10149 = add i32 0, 0
	%x10150 = add i32 0, 5
	%x10151 = mul i32 %x10150, 1
	%x10152 = add i32 %x10149, %x10151
	%x10153 = getelementptr i32,i32* %x10148, i32 %x10152
	%x10154 = load i32, i32* %x10153
	%x10155 = add i32 0, 0
	%x10156 = call i32 @dfs(i32 %x10126, i32 %x10133, i32 %x10140, i32 %x10147, i32 %x10154, i32 %x10155)
	store i32 %x10156, i32* %x10119
	%x10157 = load i32, i32* %x10119
	call void @putint(i32 %x10157)
	%x10158 = add i32 0, 0
	ret i32 %x10158
}
