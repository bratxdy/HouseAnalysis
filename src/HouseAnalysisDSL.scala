
package houseanalysis

import scala.collection._
import collection.mutable.HashMap
import java.text.DecimalFormat
import scala.util.control.Breaks._

class HouseAnalysisDSL {
  
  abstract class Person {
    var name: String = ""
    var interestRate: Double = 0.0
    
    var lessPriorities = mutable.MutableList[String]()
    var morePriorities = mutable.MutableList[String]()
  }
  
  object I extends Person {
    
    def am(n: String) = {
      name = n
      
      HaveGetter
    }
    
    object HaveGetter {
      def and(h: HaveType) = {
        AnGetter
      }
      
      object AnGetter {
        def an(i: InterestRateType) = {
          RateGetter
        }
        
        object RateGetter {
          def of(rate: Double) = {
            interestRate = rate
            
            I
          }
        }
      }
    }
  }
  
  object My {
    def priorities(a: AreType) = {
      PriorityGetter
    }
    
    object PriorityGetter {
      def less(key: String) = {
        I.lessPriorities += key
        
        PriorityGetter
      }
      
      def more(key: String) = {
        I.morePriorities += key
        
        PriorityGetter
      }
    }
  }
  
  class House() {
    
    def this(n: String, c: Double, tr: Double, hf: Double, ic: Double, fm: HashMap[String, Double]) = {
      this();
      
      name = n;
      cost = c;
      taxRate = tr;
      hoaFee = hf;
      insuranceCost = ic;
      featuresMap = fm;
    }
    
    var name: String = ""
    var cost: Double = 0.0
    var taxRate: Double = 0.0
    var hoaFee: Double = 0.0
    var insuranceCost: Double = 0.0
    var featuresMap = new HashMap[String, Double]() { override def default(key: String) = 0.0 }
    var points = 0
  }
  
  object house extends House {
    
    def copy() = {
      new House(house.name, house.cost, house.taxRate, house.hoaFee, house.insuranceCost, house.featuresMap)
    }
    
    def -(n: String) = {
      name = n
        
      house
    }
    
    def costs(c: Double) = {
      cost = c
      featuresMap += ("cost" -> cost)
      
      house
    }
    
    def hoafee(fee: Double) = {
      hoaFee = fee
      featuresMap += ("hoa" -> hoaFee)
      
      house
    }
    
    def taxrate(rate: Double) = {
      taxRate = rate
      featuresMap += ("tax" -> taxRate)
      
      house
    }
    
    def insurance(ic: Double) = {
      insuranceCost = ic
      featuresMap += ("insurance" -> insuranceCost)
      
      house
    }
    
    def has(key: String, value: Double) = {
      featuresMap += (key -> value)
      
      house
    }
  }
  
  abstract class ConsiderHouses {
    var houses = mutable.MutableList[House]()
  }
  
  object consider extends ConsiderHouses {
    def the(h: House) = {
      // Copy the house into new house
      var newHouse = house copy
      
      // Reset the house singleton
      house.name = ""
      house.cost = 0.0
      house.taxRate = 0.0
      house.hoaFee = 0.0
      house.insuranceCost = 0.0
      house.featuresMap = new HashMap[String, Double]()
      
      houses += newHouse
    }
  }
  
  object what {
    
    var p: Person = null
    var h: House = null
    var years: Int = 30
    var downPayment: Double = 0.0
    
    def would(person: Person) = {
      p = person
      
      MonthlyPayment
    }

    object MonthlyPayment {
      def pay(m: MonthlyType) = {
        Calculate
      }
      
      object Calculate {
        def on(_h: House) = {
          h = _h
          
          WithGetter
        }
        
        object WithGetter {
          def on(at: AType) = {
            LoanTypeGetter
          }
          
          object LoanTypeGetter {
            def fifteen(y: YearType) = {
              years = 15
              
              LoanGetter
            }
            
            def thirty(y: YearType) = {
              years = 30
              
              LoanGetter
            }
            
            object LoanGetter {
              def loan(a: AndType) = {
                DownPaymentGetter
              }
              
              object DownPaymentGetter {
                def put(dp: Double) = {
                  downPayment = dp
                  
                  Calculate
                }
                
                object Calculate {
                  def down() = {
                    /* M = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]
          						 M = monthly mortgage payment
											 P = the principal, or the initial amount you borrowed.
											 i = your monthly interest rate.
											 n = the number of payments over the life of the loan.
										*/
                    
                    var P = h.cost - h.cost * downPayment;
                    var i = p.interestRate / 12;
                    var n = years * 12;
                    var tax = h.cost * h.taxRate / 12;
                    var insurance = h.insuranceCost / 12;
                    var hoa = h.hoaFee;
                    var M = P * (i * Math.pow((1 + i), n)) / (Math.pow((1 + i), n) - 1) + tax + insurance + hoa;
                    
                    val formatter = new DecimalFormat("#.##")
                    println(p.name + " would pay $" + formatter.format(M) + " monthly for the " + h.name + " house.\n");
                    
                    M
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  object all {
    def houses(c: ConsideredType) = {
      WhichGetter
    }
    
    object WhichGetter {
      def which(_h: House) = {
        ShouldGetter
      }
      
      object ShouldGetter {
        def should(p: Person) = {
          ConsiderAlgorithm
        }
          
        object ConsiderAlgorithm {

          def buy() = {
            calculatePoints(I.lessPriorities, lessFunc)
            calculatePoints(I.morePriorities, moreFunc)
            
            var houses = houseToBuy()
            printHouseToBuy(houses)
            
            houses
          }
        }
        
        def lessFunc(lhs: Double, rhs: Double) = {
           lhs < rhs
        }
          
        def moreFunc(lhs: Double, rhs: Double) = {
          lhs > rhs
        }
          
        def calculatePoints(priorities: mutable.MutableList[String], f: (Double, Double) => Boolean) = {
          var houses = consider.houses
            
          // 1st place gets 3 points, 2nd place gets 2 points, 3rd place gets 1 point.
          for (p <- priorities) {
            var leaderboard = mutable.MutableList[(House, Double)]()
              
            for (h <- houses) {
              // House algorithm
              var value = h.featuresMap(p)
                  
              if (leaderboard.size > 0 && f(value, leaderboard(0)._2)) {
                leaderboard = mutable.MutableList((h, value)) ++ leaderboard
              }
              else if (leaderboard.size > 1 && f(value, leaderboard(1)._2)) {
                var lpair = leaderboard.splitAt(1)
                leaderboard = lpair._1 ++ mutable.MutableList((h, value)) ++ lpair._2
              }
              else if (leaderboard.size > 2 && f(value, leaderboard(2)._2)) {
                var lpair = leaderboard.splitAt(2)
                leaderboard = lpair._1 ++ mutable.MutableList((h, value)) ++ lpair._2
              }
              else {
                leaderboard = leaderboard ++ mutable.MutableList((h, value))
              }
            }
            
            giveOutPoints(leaderboard)
          }
        }
        
        def giveOutPoints(leaderboard: mutable.MutableList[(House, Double)]) = { 
          if (leaderboard.size > 0) {          
            // We need to deal with ties adequately.
            var pointsToGive = 3
            var currentPlaceValue = leaderboard(0)._2
            for (pair <- leaderboard) {
              var h = pair._1
              var v = pair._2
              
              // Ties get the same points, when value changes decrement points
              if (v != currentPlaceValue) {
                pointsToGive -= 1
                if (pointsToGive == 0) {
                  break
                }
              }
              
              h.points += pointsToGive
            }
          }
        }
        
        def houseToBuy() = {
          var houses = consider.houses
          var winnerPoints = 0
          
          var winners = mutable.MutableList[House]()
          
          for (h <- houses) {
            if (h.points > winnerPoints) {
              winnerPoints = h.points
              winners = mutable.MutableList[House](h)
            }
            else if (h.points == winnerPoints) {
              winners += h
            }              
          }
                      
          winners
        }
        
        def printHouseToBuy(houses: mutable.MutableList[House]) = {
          if (houses.size > 0) {
            print("You should buy the " + houses(0).name)
          }
          
          for (h <- houses drop 1) {
            print(" or " + h.name)
          }
          
          println(" house.")
        }
      }
    }
  }
}