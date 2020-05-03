# StringCalculator Mockito Kata
This kata is intended to get you started with Mockito. Start from [String Calculator Kata](http://osherove.com/tdd-kata-1). Test first!

1. StringCalculator should receive a Logger interface in it's constructor. Write the interface yourself.
2. Log each result of call to add() with log.info(String message). For example "1,1" should result in log.info("2"). Tip: Use verify().
3. Make sure that exceptions from log.info propagates out of add(). Use a RuntimeException("ERROR") and make sure the message is correct. Tip: use doThrow().when() and assertThatThrownBy().
4. add should prepend this.tostring() to each call to log.info. Should look like "…StringCalculator@45c8e616: 3", where 3 is the result. Tip: Use the test in 2, use a captor and assertThat().matches().
5. Use MockitoRunner and inject the Logger and Captor into the Test (instead of using mock() and ArgumentCaptor.forClass().
