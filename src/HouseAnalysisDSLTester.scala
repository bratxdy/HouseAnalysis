import houseanalysis._

object HouseAnalysisDSLTester extends HouseAnalysisDSL {
  def main(args: Array[String]) = {
    I am "Brady Miller" and have an InterestRate of 0.04125
    
    My priorities are less "cost" less "hoa" more "bedrooms" more "yard" more "master_bath" less "distance_to_downtown"
    
    house - "e50th" costs 619000 taxrate 0.023 insurance 1000 has ("bedrooms", 4) has ("yard", 1) has ("master_bath", 1) has ("distance_to_downtown", 4.0)

    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
        
    house - "dulce" costs 390000 taxrate 0.023 hoafee 225 insurance 1000 has ("bedrooms", 2) has ("yard", 0) has ("master_bath", 1) has ("distance_to_downtown", 3.5)
    
    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
    
    house - "e12th" costs 475000 taxrate 0.023 hoafee 100 insurance 1000 has ("bedrooms", 3) has ("yard", 1) has ("master_bath", 1) has ("distance_to_downtown", 2.0)
    
    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
        
    all houses considered which house should I buy
    
    println("\nDone")
  }
}