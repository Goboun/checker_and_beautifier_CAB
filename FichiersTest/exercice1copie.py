#!/usr/bin/python3
# -*- coding: utf-8 -*-
#!/usr/bin/env python3

import math

## longueur par défaut du coté d'un carré
DEFAULT_LENGTH = 2


def perimetreRectangle(longueur: int, largeur: int):
	"""!
	@brief
	@version 
	@author 
	@param
	@return
	@see
	"""
	return 2 * longueur + 2 * largeur


def surfaceRectangle(longueur: int, largeur: int) -> int:
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
	return longueur * largeur


def perimetreDisque(rayon: float) -> float:
	"""!
	@brief perimetre d'un disque.
	calcule le perimetre d'un rectangle
	@version 0.1
	@author ML
	@param rayon (float) la longueur du rayon
	@return le perimetre du disque
	"""
	return 2 * math.pi * rayon


def surfaceDisque(rayon: float) -> float:
	"""!
	@brief surface d'un disque.
	calcule la surface d'un disque
	@version 0.1
	@author ML
	@param rayon la longueur du rayon
	@return la surface du disque
	"""
	return math.pi*pow(rayon, 2)


def afficherCaracteristiquesRectange(longueur: int, largeur: int) -> None:
	"""!
	@brief caracteristiques d'un rectangle.
	affiche les caracteristiques d'un rectangle
	@version 0.1
	@author ML
	@param longueur la longueur du rectangle
	@param largeur la largeur du rectangle
	"""
	print("Rectangle de longueur " + str(longueur)
		+ " et de largeur " + str(largeur))
	print(" de perimetre : ", perimetreRectangle(longueur, largeur))
	print(" de surface : ", surfaceRectangle(longueur, largeur))


def afficherCaracteristiquesCarre(cote: int = DEFAULT_LENGTH) -> None:
	"""!
	@brief caracteristiques d'un carre.
	affiche les caracteristiques d'un **carre** \n
	*cette fonction réutilise les fonctions de calcul d'un rectangle
	puisqu'un carre est un rectangle (mais le contraire est faux)*
	@see perimetreRectangle
	@see surfaceRectangle
	@version 0.1
	@author ML
	@param cote (int) la longueur du cote
	"""
	print("Carre de cote ", cote)
	print(" de perimetre : ", perimetreRectangle(cote, cote))
	print(" de surface : ", surfaceRectangle(cote, cote))


def afficherCaracteristiquesDisque(rayon: float) -> None:
	"""!
	@brief
	@version
	@author
	@param
	@return
	@see
	"""
	print("Disque de rayon ", rayon)
	print(" de perimetre : ", perimetreDisque(rayon))
	print(" de surface : ", surfaceDisque(rayon))


# programme principal
if __name__ == "__main__":
	lng: int = 7
	lr: int = 2
	cote: int = 5
	ray: float = 1.2
	afficherCaracteristiquesRectange(lng, lr)
	afficherCaracteristiquesCarre(cote)
	afficherCaracteristiquesCarre()
	afficherCaracteristiquesDisque(ray)
