# Projet Kruskal avec Union-Find

## 📋 Description

Implémentation de l'algorithme de Kruskal avec trois structures Union-Find différentes pour analyser l'impact du choix de structure de données sur les performances.

## 🔧 Compilation et Exécution

### Compilation
```bash
javac *.java
```

### Exécution (conforme aux consignes)
```bash
java PerformanceTest
```

### Avec plus de mémoire pour n=10000
```bash
java -Xmx4g PerformanceTest
```

## 📊 Fonctionnalités (conformes aux consignes)

✅ **6 instances générées** : n=10, 1000, 10000 (peu dense + dense)  
✅ **Lecture fichier graphe.txt** : Format spécifié  
✅ **Affichage détaillé** : Poids MST + liste des arêtes  
✅ **Mesures séparées** : Temps tri + temps Union-Find  
✅ **Contrainte connectivité** : Arêtes consécutives imposées  

## 📁 Format du fichier graphe.txt

```
<nombre_de_sommets>
<nombre_d_arêtes>
<sommet1> <sommet2> <poids>
<sommet1> <sommet2> <poids>
...
```

**Exemple** :
```
5
7
0 1 2
0 3 6
1 2 3
1 3 8
1 4 5
2 4 7
3 4 9
```

## 📈 Sortie du programme

Pour chaque algorithme, le programme affiche :
- **Poids de l'arbre couvrant minimal**
- **Liste des arêtes sélectionnées**
- **Temps d'exécution séparés** (tri + Union-Find)

**Exemple de sortie** :
```
=== ALGORITHME FOREST ===
Poids de l'arbre couvrant minimal: 16
Arêtes sélectionnées:
  (0, 1) poids: 2
  (1, 2) poids: 3
  (1, 4) poids: 5
  (0, 3) poids: 6
Temps d'exécution - Tri: 0ms, Union-Find: 0ms, Total: 0ms
```

## 🎯 Conformité aux consignes

✅ **6 instances testées** (2 par taille : n=10, 1000, 10000)  
✅ **Lecture fichier graphe.txt**   
✅ **Affichage poids MST**   
✅ **Affichage liste arêtes**  
✅ **Mesures séparées** : Temps tri + temps Union-Find  
✅ **Contrainte connectivité** : Arêtes [0,1], [1,2], ..., [n-2,n-1]  
✅ **Poids aléatoires** : Entre 1 et 1000  

## 📊 Implémentations Union-Find

### 1. UnionFindSimple (Naïve) - O(n)
### 2. UnionFindWeighted (Union par taille) - O(log n)
### 3. UnionFindForest (Union par rang + compression) - O(α(n))
