#!/usr/bin/python3
# -*- coding: utf-8 -*-
#suite arithmetique

r = int(input("tapez raison r"))
u0 = int(input("tapez premier terme u0"))
n = int(input("tapez nombre element n"))
s = n * ((2 * u0 + (n - 1) * r)/2)
print(f"s = {s}")

print("Calcul de s à la main")

s=0
u=u0

for i in range(n) :
    s=s+u
    u=u+r

#on peut commencer avec u1, donc il faut inverser l'ordre des 2 lignes et
    #donc la range doit aller à n-1

    
    

print(s)
