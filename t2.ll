declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
    %x1 = alloca i32
      %x2 = alloca i32
        %x3 = add i32 0, 0
          store i32 %x3, i32* %x1
            %x4 = add i32 0, 4
              store i32 %x4, i32* %x2
                %x5 = load i32, i32* %x1
                  %x6 = add i32 0, 4
                    %x7 = sub i32 0, %x6
                      %x8 = sub i32 %x5, %x7
                        %x9 = load i32, i32* %x2
                          %x10 = add i32 %x8, %x9
                            store i32 %x10, i32* %x1
                              %x11 = load i32, i32* %x1
                                %x12 = icmp eq i32 0, %x11
                                  %x13 = zext i1 %x12 to i32
                                    %x14 = icmp eq i32 0, %x13
                                      %x15 = zext i1 %x14 to i32
                                        %x16 = icmp eq i32 0, %x15
                                          %x17 = zext i1 %x16 to i32
                                            %x18 = sub i32 0, %x17
                                              %x19 = icmp ne i32 0, %x18
                                                br i1 %x19, label %x20, %x21

                                                x20:
                                                  %x22 = add i32 0, 1
                                                    %x23 = sub i32 0, %x22
                                                      %x24 = sub i32 0, %x23
                                                        %x25 = sub i32 0, %x24
                                                          store i32 %x25, i32* %x1
                                                            br label %x26

                                                            x21:
                                                              %x27 = add i32 0, 0
                                                                %x28 = load i32, i32* %x2
                                                                  %x29 = add i32 %x27, %x28
                                                                    store i32 %x29, i32* %x1
                                                                      br label %x26

                                                                      x26:
                                                                        %x30 = load i32, i32* %x1
                                                                          call void @putint(i32 %x30)
                                                                            %x31 = add i32 0, 0
                                                                              ret i32 %x31
}
