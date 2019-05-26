#! /usr/bin/python
#coding:utf8

import argparse
from PIL import Image

def handle_command():
    '命令行参数处理'
    parser = argparse.ArgumentParser()
    parser.add_argument('filename',help = '图片的路径')
    parser.add_argument('-o','--output',help = '是否输出文件')
    parser.add_argument('--width',type = int,default = 80)
    parser.add_argument('--height',type = int,default = 80)

    #获取命令行参数
    return parser.parse_args()

args = handle_command()

class Ptrancefrom(object):
    '实现将图片转化为字符'
    def __init__(self,img,width,height):
        self.img = img
        self.width = width
        self.height = height
        self.ascii_char = list("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,\"^`'. ")

    def get_char(self,r,b,g,alpha = 256):
        '将256范围的灰度值映射到70个字符上'
        #灰度值为0时对应字符为空格
        if alpha == 0:
            return ' '

        length = len(self.ascii_char)
        #灰度值的计算公式
        gray = int(0.2126 *r + 0.7152*g + 0.0722*b)

        unit = (256.0 + 1)/length
        return self.ascii_char[int(gray/unit)]

    def print_picture(self):
        '打印图形'
        #打开图片
        im = Image.open(self.img)
        #设置图片像素的大小
        im = im.resize((self.width,self.height),Image.NEAREST)

        txt = ""

        for i in range(self.height):
            for j in range(self.width):
                txt += self.get_char(*im.getpixel((j,i)))

            txt += '\n'

        print(txt)

    def write_to_file(self):
        '将生成的字符图片写入到文件'
        if args.output:
            with open(args.output,'w') as f:
                f.write(txt)
        else:
            with open('output.txt','w') as f:
                f.write(txt)

pic = Ptrancefrom(args.filename,args.width,args.height)

pic.print_picture()