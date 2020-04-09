import xlwt
import sys

header = ['序号','姓名','电话号码']
data = [' ']*int((len(sys.argv)-2)/2)

for i in range(int((len(sys.argv) - 1) / 2)):
    data[i] = {'id':int(i+1), 'name':sys.argv[(i*2)+1], 'num':sys.argv[(i*2)+2]}
print(str(data))


book = xlwt.Workbook(encoding='utf-8', style_compression=0) # Object workbook
sheet = book.add_sheet('test', cell_overwrite_ok=True) # Sheet settings

# heading
i = 0
for k in header:
    sheet.write(0, i, k)
    i = i + 1

# write data into excel
row = 1
for val in data:
    print(val)
    sheet.write(row, 0, val['id'])  
    sheet.write(row, 1, val['name'])  
    sheet.write(row, 2, val['num'])  
    row = row + 1

#save
book.save(r'%s.xls'%(sys.argv[len(sys.argv)-1]))