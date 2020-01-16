Conference Track Management

You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.
The conference has multiple tracks each of which has a morning and afternoon session.
Each session contains multiple talks.
Morning sessions begin at 9am and must finish before 12 noon, for lunch.
Afternoon sessions begin at 1pm and must finish in time for the networking event.
The networking event can start no earlier than 4:00 and no later than 5:00.
No talk title has numbers in it.
All talk lengths are either in minutes (not hours) or lightning (5 minutes).
Presenters will be very punctual; there needs to be no gap between sessions.

Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don’t need to exactly duplicate the sample output given here.

翻译：您正在计划一个大型的编程会议，并且已经收到了许多提案，这些提案已经通过了最初的筛选流程，但是您却无法在一天的时间限制内完成它们——有太多的可能性了!所以你写了一个程序来帮你做。
会议有多个轨道，每个轨道有上午和下午的会议。
每个会话包含多个对话。
上午的课程从上午9点开始，中午12点前结束。
下午的课程从下午1点开始，必须在网络活动之前及时结束。
网络活动可以在4点之前开始，也可以在5点之前开始。
没有一个演讲的标题里有数字。
所有演讲的长度都是以分钟(不是小时)或闪电(5分钟)表示的。
演讲者会非常准时;会话之间不需要有间隙。
请注意，根据您选择如何完成此问题，您的解决方案可能会将不同的谈话排序或组合成轨道。这是可以接受的;您不需要完全复制这里给出的示例输出。

Test input:
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min
 
Test output:
Track 1:
09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event
 
Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event

1、进行功能验证前,先调整好控制台输出编码格式为UTF-8,否则对于异常抛出，可能输出中文乱码
2、本项目为:Problem Two: Conference Track Management
3、上传文件路径:conference/src/test/resources/test.txt
4、结果输出路径:conference/target/test-classes/out_test.txt
5、测试用例1:conference/src/test/resources/test.txt:正常数据
6、测试用例2:conference/src/test/resources/testException.txt:异常数据
7、项目总方法:TrackStrategyController:arrangeManageConference
8、目录结构
   com.thoughtworks.conference.util:存放公共类和集成方法
   com.thoughtworks.conference.model:存放此会议安排抽象出的两个数据模型：会议Conference;会话:talk;
   com.thoughtworks.conference.controller:控制类
        TrackStrategyController:追踪策略：根据一定规则，将会话安排到会议中,并实现输出
            1).文件读取，并转成TalkList对象
            2).talk按照会话时长倒序，同时加入：Lunch,Networking Event
            3).顺序获取talkList中的talk,根据时长talkDuration,安排会议.
                3.1）初始化Conference:类型上午，剩余时长180分钟，起始时间：09:00:00，截止时间:12:00:00
                3.2）循环将talk安排到Conference
                3.3) 上午时长用完，或不够之后，再新增一个Conference,类型下午，剩余时长240分钟，起始时间：13:00:00，截止时间:17:00:00
                3.4) 继续将talk安排到Conference
                3.5）Conference按照类型：0：上午;1:下午；交替创建
9、整个Conference的组成结构，参考HashMap的底层数据结构：数组+链表；当然此处为：链表+链表




