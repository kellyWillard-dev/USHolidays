<html>
<body>
<h1>Holidays</h1>
<h2>Description</h2>
<p>The Holidays API is designed to provide U.S holiday dates for any given year without using a database.</p>
<h3>USHolidays</h3>
<p>USHolidays class calculates all U.S. federal holidays for any given year.<br/></p>
<h5>Methods</h5>
<li>getChristmasDay
<li>getColumbusDay
<li>getIndependenceDay
<li>getJuneteenthDay
<li>getLaborDay
<li>getMartinLutherKingJrDay
<li>getMemorialDay
<li>getNewYearsDay
<li>getPresidentsDay
<li>getThanksgivingDay
<li>getVeteransDay
<br/><br/>
<h3>FederalHolidays</h3>
<p>FederalHolidays class extends USHolidays and determines if a specified date is a U.S. holiday.</p>
<h5>Methods</h5>
<li>isHoliday returns true if the specified date is a holiday else false.
<li>whichHoliday returns the Holiday object if the specified date matches the holiday specified else null.
<br/><br/>
<h3>ObservableHolidays</h3>
<p>ObservableHolidays class extends FederalHolidays and provides the ability to not observe specified holidays.</p>
<h5>Methods</h5>
<li>holidayUnobserved will make a specified holiday unobserved.
<li>isUnobserved returns true if a holiday is unobserved else false.
<br/><br/>
<h3>Author: Kelly Willard<br/>&nbsp;&nbsp;Email: wrk.kelly.willard@gmail.com</h3>
</body>
</html>
