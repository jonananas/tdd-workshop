# StringCalculator Mockito Kata
This kata is intended to get you started with Mockito.Start from[String Calculator Kata](http://osherove.com/tdd-kata-1). Test first!

1 Log each call to add() with log.info, output result.
2 Make sure that exceptions from log.info propagates out of add().
3 log.info should output object.tostring() each call. Should look like "…StringCalculator@45c8e616: 3", where 3 is the result.
4 Use MockitoRunner and inject the Logger and Captor into the Test (instead of using mock() and ArgumentCaptor.forClass().
