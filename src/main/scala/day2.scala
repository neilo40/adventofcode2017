/*
As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to be idle.
Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to display an hourglass cursor!"

The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track,
they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value
and the smallest value; the checksum is the sum of all of these differences.

For example, given the following spreadsheet:

5 1 9 5
7 5 3
2 4 6 8
The first row's largest and smallest values are 9 and 1, and their difference is 8.
The second row's largest and smallest values are 7 and 3, and their difference is 4.
The third row's difference is 6.
In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.

What is the checksum for the spreadsheet in your puzzle input?

--- Part Two ---

"Great work; looks like we're on the right track after all. Here's a star for your effort." However, the program seems
a little worried. Can programs be worried?

"Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in
 the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise
 operations."

It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where
the result of the division operation is a whole number. They would like you to find those numbers on each line, divide
them, and add up each line's result.

For example, given the following spreadsheet:

5 9 2 8
9 4 7 3
3 8 6 5
In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
In the second row, the two numbers are 9 and 3; the result is 3.
In the third row, the result is 2.
In this example, the sum of the results would be 4 + 3 + 2 = 9.

What is the sum of each row's result in your puzzle input?
 */

object inputDay2 {
  val list = List("790	99	345	1080	32	143	1085	984	553	98	123	97	197	886	125	947",
    "302	463	59	58	55	87	508	54	472	63	469	419	424	331	337	72",
    "899	962	77	1127	62	530	78	880	129	1014	93	148	239	288	357	424",
    "2417	2755	254	3886	5336	3655	5798	3273	5016	178	270	6511	223	5391	1342	2377",
    "68	3002	3307	166	275	1989	1611	364	157	144	3771	1267	3188	3149	156	3454",
    "1088	1261	21	1063	1173	278	1164	207	237	1230	1185	431	232	660	195	1246",
    "49	1100	136	1491	647	1486	112	1278	53	1564	1147	1068	809	1638	138	117",
    "158	3216	1972	2646	3181	785	2937	365	611	1977	1199	2972	201	2432	186	160",
    "244	86	61	38	58	71	243	52	245	264	209	265	308	80	126	129",
    "1317	792	74	111	1721	252	1082	1881	1349	94	891	1458	331	1691	89	1724",
    "3798	202	3140	3468	1486	2073	3872	3190	3481	3760	2876	182	2772	226	3753	188",
    "2272	6876	6759	218	272	4095	4712	6244	4889	2037	234	223	6858	3499	2358	439",
    "792	230	886	824	762	895	99	799	94	110	747	635	91	406	89	157",
    "2074	237	1668	1961	170	2292	2079	1371	1909	221	2039	1022	193	2195	1395	2123",
    "8447	203	1806	6777	278	2850	1232	6369	398	235	212	992	7520	7304	7852	520",
    "3928	107	3406	123	2111	2749	223	125	134	146	3875	1357	508	1534	4002	4417")
}

object day2 extends App {

  def getRows: List[List[Int]] = inputDay2.list map { row => (row.split("\t") map { n => n.toInt }).toList }

  val rowAnswers = getRows map { row =>
    val r = row.sorted
    r.last - r.head
  }

  println(rowAnswers.sum)
}

object day2_2 extends App {

  def isEvenlyDivisible(num1: Int, num2: Int): Boolean = {
    val remainder = num1 % num2
    remainder == 0
  }

  def getPairs(row: List[Int]): (Int, Int) = {
    row foreach { num1 =>
      row foreach { num2 =>
        if (num1 != num2 && isEvenlyDivisible(num1, num2)) return (num1, num2)
      }
    }
    (0, 0)
  }

  val pairs = day2.getRows map { row => getPairs(row) }
  val answers = pairs map { pair => pair._1 / pair._2 }

  println(answers.sum)
}