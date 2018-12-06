#Spring JMS Session
> 通过Session进行事务管理
> 通常Session是Thread-bound
> 事务上下文： 在一个线程中的一个Session

# Spring JMS事务类型
> Session 管理的事务-native transaction
> 外部管理的事务： JmsTransactionManager， JTA
