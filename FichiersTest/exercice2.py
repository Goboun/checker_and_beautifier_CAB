#!/usr/bin/python3
# -*- coding: utf-8 -*-

#version float
print("ecrire la 1ere valeur")
a = float(input())
print("ecrire la 2ere valeur")
b = float(input())
c = (a + b)/2
print(f"la moyenne version float est {c}")

#version entier (integer)
print("ecrire la 1ere valeur")
a = int(input())
print("ecrire la 2ere valeur")
b = int(input())
c = (a + b)/2
print(f"la moyenne version integer est {c}")
c = (a + b)//2
print(f"la moyenne entiere est {c}")
c = (a + b)%2
print(f"le reste entier est {c}")
