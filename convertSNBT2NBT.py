import nbtlib
from nbtlib import parse_nbt

# 1. Charger ton fichier SNBT brut
with open("ggg.snbt", "r", encoding="utf-8") as f:
    snbt_text = f.read()

# 2. Parser le SNBT en structure NBT (renvoie un Compound avec size/palette/blocks/entities/DataVersion)
compound = parse_nbt(snbt_text)

# 3. Créer un fichier NBT avec CE compound comme racine
nbt_file = nbtlib.File(compound)

# 4. Sauvegarder en NBT GZIPPÉ (important pour les structures dans data/<modid>/structures)
nbt_file.save("ggg.nbt", gzipped=True)

print("Conversion réussie ! Fichier généré : ggg.nbt")
