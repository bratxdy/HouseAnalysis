import houseanalysis._

object HouseAnalysisDSLTester extends HouseAnalysisDSL {
  def main(args: Array[String]) = {
    I am "Average Joe" and have a 0.04125 interest rate and budget of 600000
    
    My priorities are more "bedrooms" bigger "yard" better "master bath" less "distance to downtown"
    
    house - "e50th" costs 619000 and has a 0.023 tax rate and insurance costs 1000 dollars yearly
    
    4 ct "bedrooms"
    .35 acre "yard"
    10 rated "master bath"
    4 mi "distance to downtown"

    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
        
    house - "dulce" costs 390000 and has a 0.023 tax rate and insurance costs 80 dollars monthly and HOA costs 225 dollars monthly
    
    2 ct "bedrooms"
    4 rated "master bath"
    3.5 mi "distance to downtown"
    
    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
    
    house - "e12th" costs 475000 and has a 0.023 tax rate and insurance costs 1000 dollars yearly and HOA costs 250 dollars quarterly
    
    3 ct "bedrooms"
    .10 acre "yard"
    8 rated "master bath"
    5.0 mi "distance to downtown"
    
    what would I pay monthly on house on a thirty year loan and put .20 down
    
    consider the house
        
    all houses considered which house should I buy
    
    println("\nThanks for shopping.")
  }
}