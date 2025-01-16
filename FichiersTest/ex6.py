#!/usr/bin/python3
# -*- coding: utf-8 -*-
#Exercice 1

def creeTab(n) -> int:
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
    tab = [[0 for j in range(n)]for i in range (n)]
    for i in range(n):
        for j in range(n):
            if (i==j):
                tab[i][j] = 1
            elif (i>j):
                tab[i][j] = 2
    return tab
    
def affiche(tab): ->
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
    for i in range(len(tab)):
        for j in range (len(tab[0])):
            print(tab[i][j], end = " ")
        print()

###Programme Principal
##
##n = int(input("La valeur svp: "))
##tab = creeTab(n)
##affiche(tab)

#Exercice 2 - B = Black, W = White

def creePlateau(n):
	"""!
	@brief
	@version 
	@author 
	@param
	@return
	@see
	"""

    tab = [["W" for j in range(n)]for i in range (n)]
    for i in range(n):
        for j in range(n):
            if ((i+j)%2):
                tab[i][j] = "B"
    return tab

def creePion(tab):
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
    #Pions noirs
    for i in range (4):
        for j in range(n):
            if ((i+j)%2):
                tab[i][j] = "1"
    #Pions blancs
    for i in range (6,10):
        for j in range(n):
            if ((i+j)%2 == True):
                tab[i][j] = "2"    

###Programme Principal
##n = 10
##plateau = creePlateau(n)
##creePion(plateau)
##affiche(plateau)

#Exercice 3

def stealID(nyeh) -> int:
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
    intiales = nyeh[0]
    for i in range (len(nyeh)):
        if(nyeh[i] == " " or nyeh[i] == "-"):
            initiales += nyeh[i+1]
    return initiales

#Programme Principal

identite = input("Votre nom complet avec votre nom de famille en premier et pas sous un format anglais avec le prenom en premier")

truc = stealID(identite)
for i in range (len(truc)):
    print(f"{i}", end = " ")
