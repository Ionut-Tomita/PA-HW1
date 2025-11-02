# Proiectarea Algoritmilor - Tema 1

## Problema 1 - Alimentare servere

In urma analizei enuntului problemei 1 am ajuns la concluzia ca acel curent
optim care ar conduce la o putere maxima a sistemului se afla intre valoarea
minima si cea maxima din vectorul de curenti la care sunt alimentate serverele.

Am extras minimul si maximul din acel vector si am aflat curentul mediu asupra
caruia am aplicat recursiv functia "getBestPower" pana cand se gaseste acel
curent optim, cu o eroare de 0.1 (se face comparatia cu 0.01, deoarece a doua
zecimala poate sa fluctueze)

Functia "getBestPower" se bazaeaza pe conceptul de Divide et Impera, imparte
alonja de cautare in doua si alege partea in care se afla curentul optim. Se
face comparatia intre puterea maxima generata de curentul din stanga si dreapta
mijlocului si se alege partea in care puterea generata este mai mare, aceasta
fiind directia buna catre gasirea curentului optim de alimentare. Se apeleaza
recursiv pana se gaseste curentul cu o eroare de 0.1.

- Complexitatea algoritmului este O(log(n)).


## Problema 2 - Colorare

In urma analizei enuntului problemei 2 am ajuns la urmatoarele concluzii:

1) In functie de tip, avand posibilitateade de a alege intre cele 3 culori
   astfel incat sa se respect conditiile problemei, prima zona poate fi colorata
   in 6 moduri distincte pentru 'H' (orizontala) si 3 moduri distincte pentru
   'V' (verticala).

2) In cazul in care zona curenta si cea precedenta sunt de acelasi tip (zonele
   din aceeasi pereche), zona curenta poate fi colorata in maxim 3 moduri distincte
   pentru 'H' si 2 moduri distincte pentru 'V'. (Pentru a optimiza ridicarea la
   putere ce apare in urma inmultirilor repetate am creat functia "power"), ce
   se regaseste si in laboratorul 1.(Exponentiere logaritmica)

3) In cazul in care zona curenta si cea precedenta sunt de tipuri diferite,
   zona curenta poate fi colorata in maxim 2 moduri distincte pentru daca aceasta
   este de tipul 'H' iar zona precedenta de tipul 'V' doar un singur mod de
   colorare este posibil pentru zona curenta de tipul 'V' daca zona precedenta
   este de tipul 'H'.


TLDR:
- Zona initiala:
    - 6 moduri de colorare pentru 'H'
    - 3 moduri de colorare pentru 'V'


- Zona curenta si precedenta de acelasi tip:
    - 3 moduri de colorare pentru 'H'
    - 2 moduri de colorare pentru 'V'


- Zona curenta si precedenta de tipuri diferite:
    - 2 moduri de colorare pentru 'H' curent cand 'V' precedent
    - 1 mod de colorare pentru 'V' curent cand 'H' precedent


- Complexitatea algoritmului este O(n), unde n este dat de numarul de
  perechi(nrZone, tip) citite din fisierul de intrare.


## Problema 3 - Compresie

Pentru aceasta problema am folosit 2 sume, sumA si sumB, care ma vor ajuta sa
inlocuiesc o subsecventa cu suma elementelor ei, iar daca aceste sume sunt
egale sa adaug suma ca element nou intr-un ArrayList final.

Pentru inceput sumele vor fi egale cu primul element din vectorul de numere
asociat. Atata timp cat indexii sunt mai mici decat dimensiunile vectorilor
lor corespunzatori, se vor compara sumele si se va decide daca elementul
trebuie introdus in vectorul final sau nu. In cazul in care sumele difera
se va incrementa indexul corespunzator sumei mai mici, iar elementul curent
va fi introdus in suma sa.
La final se vor adauga elementele ramase in sumele asociate comparand inca
o data sumele pentru a decide daca se poate introduce ultimul element in
vectorul nou creat sau daca compresia vectorilor e imposibila si deci
crearea a doua siruri egale prin acele operatii de "compresie" este imposibila.

Se va scrie in fisierul de output lungimea vectorului final sau "-1" in caz
de imposibilitate.

- Complexitate algoritmului este liniara O(n), unde n este dimensiunea vectorului.


## Problema 4 - Criptat

Pentru aceasta problema am inceput prin a cauta litera dominanta din fiecare
cuvant. Am creat un HashMap in care am adaugat fiecare litera si numarul ei de
aparitii in acel cuvant. Am comparat numarul de aparitii ale fiecarei litere
si am actualizat litera cu cele mai multe aparitii. Dupa aceasta am verificat
daca litera este dominanta in acel cuvant, iar daca da, am slavat-o intr-un
vector cu potentialele litere dominante din parola finala.

In continuare am inceput sa construiesc parola finala pornind de la fiecare
litera candidat:
- Am sortat vectorul de cuvinte descrescator in funcctie de ponderea literei
  candidat in cuvant
- Am adaugat cuvantul la posibila parola daca prin adaugarea lui se respecta
    conditia ca litera candidat sa fie dominanta in acel cuvant

La fiecare pas se actualizeaza parola finala cu cea mai lunga parola gasita pana
in acel moment.

 - Complexitatea algoritmului este O(n * long(n)), unde n este numarul de cuvinte
din fisierul de intrare, data de sortarea cuvintelor in functie de ponderea
literei candidat. (Chiar daca se mai fac si alte operatii asupra cuvinetelor,
acestea sunt de complexitate mai redusa decat sortarea cuvintelor)
