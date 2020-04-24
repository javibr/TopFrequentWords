# TopFrequentWords

This algorithm returns the N most repeated possibleFeatures out of a featureRequest list, and in case that some words appear the same number of times, respect the lexicographic order. 

# Two approaches

## Every time that a word appears in the same featureRequest, we count it.

This case is implemented in the method **popularNFeatures**

## Every time that a word appears in the same featureRequest, we only count it once.

This case is implemented in the method **popularNFeaturesNoRepeat**
