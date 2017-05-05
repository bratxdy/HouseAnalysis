# HouseAnalysis
A Scala Internal DSL written in Scala for the CS345 final assignment. This DSL allows you to write code that closely resembles natural language to help you decide which house to buy.

# What can you do?

### Create a person with your interest rate, budget, and priorities.

`I am "Average Joe" and have a 0.04125 interest rate and budget of 600000`

This will create a person named Average Joe and set the interest rate and budget. The interest rate is optional and only used if you want to calculate mortgage payments. If you don't want to set an interest rate you can do the following:

`I am "Average Joe" and have a budget of 600000`

To set your priorities so that the algorithm can help you choose a house you must do the following:

`My priorities are more "bedrooms" bigger "yard" better "master bath" less "distance to downtown"`

The strings are the keys that you plan to add to your houses later on with their values. You can choose `more, bigger, better` or `less, smaller, worst` as the keywords for the properties.

### Create houses for the algorithm to consider.

`house - "e50th" costs 619000 and has a 0.023 tax rate and insurance costs 1000 dollars yearly and HOA costs 225 dollars monthly
    
4 ct "bedrooms"
.35 acre "yard"
10 rated "master bath"
4 mi "distance to downtown"

consider the house`

This code will create a house with the name, cost, tax rate, insurance, and HOA. Insurance, HOA, and tax rate are used in the mortgage calculation and are therefor optional. For insurance you can say `monthly` or `yearly` and for HOA you can say `monthly`, `quarterly`, or `yearly`. This will then populate the house with the four properties: `bedrooms`, `yard`, `master bath`, and `distance to downtown`. *It's important that these property strings match those set forth above in the priorities.* You can add as many properties to a house you want, but only those set up in the priorities will make a difference. The last line of code will make a copy of the house and add it to the list of houses being considered and *reset* the house object.

If you want to calculate the mortgage payment for this house you can do the following line:

`what would I pay monthly on house on a thirty year loan and put .20 down`

You can say `thirty` or `fifteen` for the loan duration and put any value 0.00-1.00 for the downpayment.

### Ask which house to buy

`all houses considered which house should I buy`

This will run the algorithm and tally up the points according to your preferences, and then make an adjustment for the cost of the house compared to your budget and print out which house you should buy along with the points each house received.
