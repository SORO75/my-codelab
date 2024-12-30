# set ist mutabel, frozenset ist immutabel

s1={1,2,3,4,5,6, 7, 8, 9}
s2 ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}

print ('Erste Menge (set):', s1)
print ('Zweite Menge (set):', s2)

#Operatoren
def lange (menge):
    print('die Menge hat:',len(menge), 'Elementen')

#
print (3 in s1, 10 in s1)
print (13 not in s1)
print (s1<=s2)
print (s1<s2)
print (s1>=s2)
print (s1>s2)

print(s1|s2)
print(s1&s2)
print(s2-s1)
print(s1^s2)


s3= s1.copy()
print (s3)

s3.add(10)
print (s3)
s3.discard (20)
s3.remove(20)
s3.remove(10)
print (s3)

lange(s1)




#frozenset 

fs_leer = frozenset()
fs_voll = ({1,2,3,4})
print (fs_leer)
print(fs_voll)