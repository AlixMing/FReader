/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : jfinal_demo

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-01-23 17:07:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activity_user` (`userId`),
  CONSTRAINT `activity_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('27', '周三读书屋', '2015-01-07 11:00:00', '2015-01-14 23:00:00', '南亭客栈读书节，一起来读书论剑吧！！！哈哈哈哈', '南亭客栈', '13');
INSERT INTO `activity` VALUES ('30', '读书交友', '2015-01-20 00:00:00', '2015-01-20 23:00:00', '华农图书馆周二欢迎您的到来', '华农图书馆2楼', '13');
INSERT INTO `activity` VALUES ('37', '好好读书', '2015-01-13 00:00:00', '2015-01-21 23:59:59', '    没事多读书\r\n    读书好处多\r\n    啊啊', '读书屋', '13');
INSERT INTO `activity` VALUES ('38', '周日读书日', '2015-01-18 00:00:00', '2015-01-25 23:59:59', '周日一起看书吧，走起！！！', '华农图书馆', '13');
INSERT INTO `activity` VALUES ('39', '广州书店送书', '2015-01-01 00:00:00', '2015-01-31 23:59:59', '一个月的时间，我们每天送上一本精美包装书籍。先到先得哦', '广州天河购书中心', '6');

-- ----------------------------
-- Table structure for activity_user
-- ----------------------------
DROP TABLE IF EXISTS `activity_user`;
CREATE TABLE `activity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `activityid` (`activityId`),
  KEY `userid` (`userId`),
  CONSTRAINT `activityid` FOREIGN KEY (`activityId`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of activity_user
-- ----------------------------

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) COLLATE utf8_bin NOT NULL,
  `content` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'a', 'aa');
INSERT INTO `blog` VALUES ('2', 'b', 'bb');
INSERT INTO `blog` VALUES ('3', 'c', 'cc');
INSERT INTO `blog` VALUES ('4', '啊', '事实上');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `author` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `picture` varchar(64) COLLATE utf8_bin DEFAULT 'upload\\book\\图片不存在.jpg',
  `url` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `descri` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `download` int(10) DEFAULT '0',
  `typeId` int(11) DEFAULT NULL,
  `recommentTime` int(10) DEFAULT '0',
  `weekRecomment` int(10) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `book_type` (`typeId`),
  CONSTRAINT `book_type` FOREIGN KEY (`typeId`) REFERENCES `type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '世界间谍史', '[日]海野 弘', 'upload\\book\\世界间谍史.jpg', 'upload\\book\\新建文本文档.txt', '世上最精细的工作就是间谍，间谍是最富有挑战和最具有智慧的职业，这本书值得一读。', '4', '1', '0', '0');
INSERT INTO `book` VALUES ('2', '渔夫和国王 ', '（印）乔克茜（Choksi.M.）', 'upload\\book\\渔夫与国王.jpg', 'upload\\book\\新建文本文档.txt', '本丛书策划引进的立意在于借鉴亚洲主要国家英语阅读形成的各自经验，挑选各国最具代表性的英语阅读经典，为中国读者提供富于启发的异国视角和参考读本。', '1', '1', '0', '0');
INSERT INTO `book` VALUES ('3', '活着还是死去', '（印）泰戈尔', 'upload\\book\\活着还是死去.jpg', 'upload\\book\\新建文本文档.txt', '《活着还是死去》一书精选了活着还是死去？我的主人，那孩子，从前有一位国王，纳偃月的大人，喀布尔的水果贩，信徒，回家，饥饿的石头，扑克牌王国，决裂，胜利，盲妻，“我们为您加冕”等。里面的故事已经问世一百多年，但读起来情节起伏跌宕，生动有趣，令人回味无穷，在许多方面仍具有很强的现实意义。活着还是死去？\r\n　　瑞尼哈特地主萨拉大萨卡家的寡妇，父亲家里已经没什么亲人了。他们一个接一个地去世了。她丈夫家里也没什么人可以称为她自己的亲人，她没了丈夫也没有儿子。大伯子萨拉大萨卡的儿子是她的心肝宝贝。在他出生后很长一段时间，他的母亲一直身体不好，他守寡的婶婶卡丹比尼就养育了他。要是一个女人抚养其他人的孩子，她对他的爱是最强烈的，因为她没有拥有他的权利——没有血缘关系，也就是说，只有以爱来留住他。爱是不能通过社会能接受的任何条约来证明，也不愿以此证明；它只是以双倍的热情来崇敬它生命中这不确定的珍宝。就这样，寡妇所有被压抑的爱都倾注到这个小孩子身上。一天晚上，萨拉班·卡丹比尼突然死了。由于某种原因她的心跳停止了。世界上其他的地方一切照旧；只有孩子这个温柔的小小胸膛里，由于爱的煎熬，时间就此永远停止。\r\n　　为了避免闹鬼，地主家四个婆罗门仆人抬走了尸体，没有任何仪式，准备烧掉。瑞尼哈特地区的焚尸地离村庄很远。在池塘边上有问小屋，附近有一棵很大的榕树，此外一无所有。之前有一条河流过这个地方，现在已完全干涸，部分水道被挖出做水池，用于葬礼仪式。人们把池塘看成是河流的一部分，同样尊敬它。\r\n　　将尸体放人小屋，四个人坐下来等木材，时间如此漫长，四个人中有两个开始烦躁不安，回去看为什么还没来。尼太和谷如茶兰走了，彼德户和巴纳纳里留下来看守尸体。\r\n　　这是萨拉班一个漆黑的夜晚。重重乌云遮盖住了星光。两人静静地坐在漆黑的房里。火柴和灯都用不上。火柴潮了，怎么也点不亮，灯又灭了。\r\n　　一段长久的沉默后，一个说：“兄弟，要是我们有一壶烟草就好了。我们在匆忙中什么也没带。”\r\n　　另一个回答说：“我可以跑回去把我们需要的都带来。”', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('5', '世界丑闻史', '海野弘', 'upload\\book\\世界丑闻史.jpg', 'upload\\book\\新建文本文档.txt', '我们看到伟人或名人栽跟头就会发笑，内心会颇感释然，原来他们也会跌倒，也会摔跟头啊。丑闻的历史实际上就是一部人类的愚蠢史。人类是多么的愚蠢和卑微啊！\r\n那就从这里开始，让我们走进人类在世界历史上留下的种种丑闻中去吧。何为丑闻，实际上问的是何为人类。人类就是丑闻的主角。人类是会栽跟头的。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('6', '《红楼梦》与中国文学传统', '王庆云著', 'upload\\book\\《红楼梦》与中国文学传统.jpg', 'upload\\book\\新建文本文档.txt', '红学作为国内古代文学和国外汉学研究的一门显学，自18世纪中期以来，尤其是自五四新文化运动之后“新红学”兴起至今，其热度一直没有降低过。检视近一个世纪红学的得失功过，承续红学传统文脉，是推进当代红学创新发展的基础。本书回顾了百年红学史上的五次高潮，对红学百年诸多尚未解开的谜团进行了探秘，评价了当代红学点评对红学传统的承继与创新的意义。<br> 　　《<红楼梦>与中国文学传统》对作为世界汉学“显学”的红学研究的发展历程及其至今存在的《红楼梦》之谜问题均作了系统的梳理研究和创新解读，问题意识凸显，内容引人入胜，值得阅读参考。', '1', '2', '0', '0');
INSERT INTO `book` VALUES ('7', '芒果树', '（印）萨卡尔（Sarkar.S.）改写 ', 'upload\\book\\芒果树.jpg', 'upload\\book\\新建文本文档.txt', '《东方朗文国际悦读系列丛书》的出版，至少有以下三个方面的意义。\r\n其一，借助阅读风格的不同，共享英语学习的他国经验。我们知道，各国学习英语，由于其思维方式，文化背景，乃至行为习惯等的诸多不同，形成的风格也就不同。我们习惯了中国的方式，如果尝试一下别国的思路或方式，或许会给您带来惊喜。印度是将英语作为社会主流语言的国家，这套丛书是专为各级不同层级的英语学习者打造的，所以读者通过阅读经印度一流出版公司、一流的改写者打造出的阅读版本，一定会有方法上的启迪，文体与表达差异会给您带来某种深刻印象。\r\n其二，将各国学习英语过程中具有代表性的名著遴选出来，减少文化上的隔阂，有利于对英语阅读的快速进入。文学名著是人类精神的财富，我们可以通过阅读具有印度特色的名著，领略他们改编的方式和编辑的视角，获得对名著阅读更多的启发。基于此，我们为中国各个层次的英语学习者，挑选了43本脍炙人口的名著，用轻松活泼的形式呈现给广大英语学习者及大中小学生。\r\n其三，考虑到中国人学习英语的水平与印度学生英语水平存在的差异，我们为中国读者重新量身打造。第一，邀请了小学、中学、大学的一线英语教师进行座谈、讨论，根据中国学生学习英语的实际情况对丛书重新进行了划分，按年级分成了十套，从小学高年级到大学；第二，为了让学生更方便、更乐于阅读，通过阅读真正提高英语水平，我们同时请这些英语教师根据教学大纲、教学经验对每本书的生词作了旁注，让学生在阅读的同时学习新词，增加词汇量；第三，对书中出现的学习考试中高频率词汇以及重点句型进行了解析，让学生在阅读的同时复习和巩固了课堂中所学的知识。此外还在每本书的最后附上了本书针对中国英语中考、高考以及其他各类考试的高频率词汇表，充分加大了本套丛书的实用性和知识性。这样，就为我们更方便、深入地进行阅读架构了桥梁。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('8', '疯狂麻花英语3', '赛珍妮 ', 'upload\\book\\疯狂麻花英语3.jpg', 'upload\\book\\新建文本文档.txt', '“疯狂麻花英语”不只是让你读来莞尔一笑，内容的出发点是让你能认识更多实用、有趣的词组、口语及惯用语；这是比背上几千个单字来得重要，而且容易的。四格漫画连贯的剧情及人物趣味、夸张的表情，让你了解到在各个语句在不同情境的正确用法，不再像以往一样，被一些语焉不详的中文翻译搞得雾煞煞!\r\n本书最适阅读对象：得通过考试才能毕业的各级学生；不想加班但想加薪的上班族；不想被小孩子鄙视的现代父母；不怕大笑惹皱纹的E世代美女；懒得背单词却想学好英文的人；想搞懂“六人行”在演什么的人；即将出国取经想增强听说能力的人；想增加“舞台效果”的英文老师。', '0', '9', '0', '0');
INSERT INTO `book` VALUES ('9', '中国书籍文学馆·精品赏析:咀嚼人生', '杨晓华', 'upload\\book\\咀嚼人生.jpg', 'upload\\book\\新建文本文档.txt', '本书精选近百年来数十位中外名家分享人生感悟、诠释人生真谛之经典散文，展卷阅读，细心品味，每一篇文章都心醉神迷。热爱生命是幸福之本，同情生命是道德之本，敬畏生命是信仰之本。看名家谈人生，获知人生的意义，感悟生命之幸福，端正对生命的态度。', '0', '6', '0', '0');
INSERT INTO `book` VALUES ('10', '积极心理学视角下的发展性心理健康教育', '向前', 'upload\\book\\积极心理学视角下的发展性心理健康教育.jpg', 'upload\\book\\新建文本文档.txt', '向前编著的《积极心理学视角下的发展性心理健康教育》共包括积极心理学概述、发展性心理健康教育概述、积极认知的形成、积极体验的丰富、积极人格的培养、积极关系的拥有、积极心理学在发展性心理健康教育中的主要操作途径等七章内容。总体来说，本书结构清晰、理论明确，具有系统性、知识性、全面性、实用性等特点。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('23', '马云正传', '刘世英,彭征', 'upload\\book\\马云正传.jpg', 'upload\\book\\马云正传.txt', '　　身材矮小的马云,却是这个世界上最大的互联网市场中的大人物.\r\n　　短短15年间,他从英语教师成长为互联网大亨,并带领阿里巴巴成为全世界最大的电子商务平台的经历,折射出中国互联网的崛起和发展历程.在中国,绝大多数的私人财富都是靠从国有部门分一杯羹获得.但是马云乘着互联网之势正在改变这一格局.他与硅谷一些有着响亮名字的人物之间的争斗,也已成为人们津津乐道的传奇故事.而他以各种\"大手笔\"搅动世界的脚步从未停止;横空出世的余额宝让银行颤栗,菜鸟物流强势来袭,阿里将成为史上最大IPO整体上市……\r\n　　马云如何从民营小个体户,成长为中国新首富?如何带领阿里巴巴成为国内市值最大的民营公司?如何创造了这么多奇迹?他又将如何在未来主导世界?本书将带你一起重温他的创业之路,解读他的如戏人生,看他如何颠覆传统,看我们的世界如何随他而动!', '2', '1', '0', '0');
INSERT INTO `book` VALUES ('24', '普京传：他为俄罗斯而生', '郑文阳', 'upload\\book\\普京传：他为俄罗斯而生.jpg', 'upload\\book\\马云正传1.txt', '\"他为俄罗斯而生，他是俄罗斯复兴的灵魂，他是一个个性鲜明的硬汉！他担任总统八年，使俄罗斯经济翻了八倍！他绝对可以被称为新俄罗斯的缔造者和舵手。当他以64.4%的高得票率第三次当选俄罗斯总统之时，竟然也饱含热泪。　　其实，普京对于很多人仍然是一个谜。本书作者研究普京多年，他力求将一个鲜活、清晰、有血有肉的普京呈现给每位读者。书中资料丰富，史实准确客观，披露了一些鲜为人知的真相，可以说是普京传记里最全、最好的读本之一。\"', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('25', '你若安好便是晴天', '白落梅', 'upload\\book\\你若安好便是晴天.jpg', 'upload\\book\\马云正传2.txt', '她是中国第一代女性建筑学家，被胡适誉为中国一代才女。她是中华人民共和国国徽设计的参与者，是人民英雄纪念碑的设计者之一，是传统景泰蓝工艺的拯救者。她是一个聪慧的女子，让徐志摩怀想了一生，让梁思成宠爱了一生，让金岳霖默默地记挂了一生，更让世间形色男子仰慕了一生。她，就是林徽因。本书用最清澈的文字、诗意的笔法、全面详实的资料，生动地展现了林徽因的传奇一生。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('26', '蒋介石传', '杨树标', 'upload\\book\\蒋介石传.jpg', 'upload\\book\\马云正传3.txt', '20世纪80年代末,拙著《蒋介石传（1887—1949）》在大陆第一本作为历史书出版,当时发行30多万册,这在史学界算是同类图书的发行盛世。进入21世纪,顺着我所学习与服务了50多年的浙江大学被批准设立“蒋介石与近代中国研究中心”——这是大陆第一个公开打出“蒋介石研究”牌子的研究机构,拙著经过修正,并由杨菁教授加盟、增添了十几万字,由浙江大学出版社再次出版,至今也已重印六次发行几万册。但很遗憾,那一著作只写到蒋介石在大陆的活动,在书的末尾,写了一千多字的“尾声”,把蒋介石在台湾的活动说了几句。 ', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('27', '一问一世界', '杨澜,朱冰', 'upload\\book\\一问一世界.jpg', 'upload\\book\\新建文本文档1.txt', '作为电视主持人和记者，我以提问为生，并以提问为乐。提问，不一定来自于无知，相反你只有知道得越多，才会问出更多更有价值的问题。这10年，我做了500多期《杨澜访谈录》，如果每个访谈人物平均要问到20个问题的话，算一下就有一万多个问题。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('28', '美国纽约摄影学院--摄影教材', ' 美国纽约摄影学院', 'upload\\book\\美国纽约摄影学院--摄影教材.jpg', 'upload\\book\\新建文本文档2.txt', '       《美国纽约摄影学院摄影教材（最新修订版2）（套装上下册）》是20世纪80年代中期以来经久不衰的摄影教材。这套教材以体系完整、内容丰富、语言贴心而著称，细细讲解了婚纱摄影、时装摄影、人体魅态摄影、广告与静物摄影、建筑摄影、缩微摄影、新闻摄影等各摄影门类的概念和技术技巧，还教授了彩色胶片冲洗与放大、机背取景照相机、照片的修饰、自由职业摄影师指南、摄影室等技巧。各章节选用了精彩的照片进行说明和赏析，可使读者的学习更加系统、更加全面。<br>       2009年8月，中国摄影出版社本着坚持原作风格和内容的原则进行了最新修订，基于数码摄影的现状和发展情况，邀请作者唐?谢夫重新编写了数码摄影部分共6课的内容，使著作更贴近时代。同时，新的《纽摄》图片的质量进一步提高，内文的版式和封面都进行了全新的装帧设计，希望能给影友们带来更新的阅读享受。', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('29', '解忧杂货店', '东野圭吾', 'upload\\book\\解忧杂货店.jpg', 'upload\\book\\马云正传4.txt', '僻静的街道上有一家店，不仅销售杂货，还提供烦恼咨询。无论你挣扎犹豫，还是绝望痛苦，欢迎来信！《解忧杂货店》堪称东野圭吾在《白夜行》后最受欢迎的作品，不但荣获中央公论文艺奖，更登上纪伊国屋、诚品、博客来、金石堂等各大排行榜第1名。东野圭吾这次选择的，是生活中最平凡的片段：在事业和爱情间艰难抉择的运动员、离家打拼却屡遭挫折的音乐人、想要挣钱报答亲人的女招待……他们真诚又忐忑地写下信件，想要为自己的未来找到新的可能。互不相识的人因一家可以咨询烦恼的杂货店而走到一起，虽未谋面，心与心却真诚相对，这正是东野圭吾想要展现的力量：现代人内心流失的东西，这家杂货店能帮你找回。《解忧杂货店》充分展现了东野圭吾的创作才华。通过书信这种已渐渐淡出人们生活的交谈方式，人物依次登场，精巧的结构让温情、惊喜与感动悄然渗入读者心中，回味无穷。随书附赠解忧杂货店主题温馨书签。<br>亚马逊编辑推荐:<br>一个能够为人们答惑解忧的杂货店，一所孤儿院，一位老人，三个小偷，还有近十位被各种忧愁困扰的男男女女。五个看似独立，实则却有着千丝万缕关联的故事。是否在传达这样的信息：时间是解决所有忧愁的良药。一切迷惘困惑，都能在时间中找到答案。故事中的人们，为了解除自己的烦恼，通过写信的方式，向杂货店的老板浪矢先生咨询。而浪矢先生也会认真回答每一封寻求帮助的来信。于是，无论是作者巧妙设计的时光交错，还是信件的一问一答，都无形中把更多的时间留给了他们。在时间中聆听，在时间中思考，在时间中迷茫，在时间中领悟，体会，选择……在时间中，一切爱与恨都会沉淀。顺流而下，随时间的脚步找到答案和真正的自己。<br>“相助”。送人玫瑰，手有余香。认真读过《解忧杂货店》的朋友，不难发现，五个故事中的人们，或阴差阳错或命运安排，都是帮助与被帮助的关系。甚至包括即将因病去世，还在帮助别人的浪矢老板，也是在被帮助者的感谢信中得到了最后的安慰和释怀。相助是一个轮回，无论出于什么目的的出手相助，只要用心和真诚，到后来都会实现自我的救助和灵魂的升华。<br>读到这里，不知你是否赞同我的观点，如果不能确定，请再次翻开《解忧杂货店》，找到属于你的牛奶箱，去寻找你自己的答案吧。<br>——雨田铃声 ', '0', '1', '0', '0');
INSERT INTO `book` VALUES ('30', '红楼梦', '曹雪芹', 'upload\\book\\红楼梦.jpg', 'upload\\book\\红楼梦.txt', '        《红楼梦》，中国古代四大名著之一，章回体长篇小说，成书于1784年（清乾隆四十九年），梦觉主人序本正式题为《红楼梦》。其原名有《石头记》、《情僧录》、《风月宝鉴》、《金陵十二钗》等。<br>       前80回曹雪芹著，后40回高鹗续（一说是无名氏续），程伟元、高鹗整理。本书是一部具有高度思想性和高度艺术性的伟大作品，作者具有初步的民主主义思想，他对现实社会、宫廷、官场的黑暗，封建贵族阶级及其家族的腐朽，对封建的科举、婚姻、奴婢、等级制度及社会统治思想等都进行了深刻的批判，并且提出了朦胧的带有初步民主主义性质的理想和主张。', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for clocks
-- ----------------------------
DROP TABLE IF EXISTS `clocks`;
CREATE TABLE `clocks` (
  `id` int(10) unsigned zerofill NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `isUse` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clocks_user` (`userId`),
  CONSTRAINT `clocks_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of clocks
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `content` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `praise` int(10) unsigned zerofill DEFAULT NULL,
  `isDelete` int(10) unsigned zerofill DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user` (`userId`),
  KEY `comment_book` (`bookId`),
  CONSTRAINT `comment_book` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '8', '1', '好看', '0000000004', '0000000000', '2015-01-21 09:10:13');
INSERT INTO `comments` VALUES ('2', '13', '1', '不错', '0000000007', '0000000000', '2015-01-21 09:19:58');
INSERT INTO `comments` VALUES ('3', '16', '1', '不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错不错', '0000000005', '0000000000', '2015-01-22 10:45:37');

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pbookId` int(11) DEFAULT NULL,
  `mark` int(10) unsigned zerofill DEFAULT NULL,
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `note_pbook` (`pbookId`),
  CONSTRAINT `note_pbook` FOREIGN KEY (`pbookId`) REFERENCES `pbook` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for pbook
-- ----------------------------
DROP TABLE IF EXISTS `pbook`;
CREATE TABLE `pbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `bookId` int(11) DEFAULT NULL,
  `progress` int(10) unsigned zerofill DEFAULT NULL,
  `isDelete` int(10) unsigned zerofill DEFAULT NULL,
  `isLocal` int(10) unsigned zerofill DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `pbook_book` (`bookId`),
  KEY `pbook_user` (`userId`),
  CONSTRAINT `pbook_book` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pbook_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pbook
-- ----------------------------
INSERT INTO `pbook` VALUES ('1', '6', '2', '0000000001', '0000000000', '0000000000', '');

-- ----------------------------
-- Table structure for recomment
-- ----------------------------
DROP TABLE IF EXISTS `recomment`;
CREATE TABLE `recomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `reason` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `recomment_user` (`userId`),
  KEY `recomment_book` (`bookId`),
  CONSTRAINT `recomment_book` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `recomment_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of recomment
-- ----------------------------

-- ----------------------------
-- Table structure for timeline
-- ----------------------------
DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `timeline_user` (`userId`),
  CONSTRAINT `timeline_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of timeline
-- ----------------------------
INSERT INTO `timeline` VALUES ('1', '6', '2015-01-21 10:47:31', '读完红楼梦');
INSERT INTO `timeline` VALUES ('2', '6', '2015-01-21 10:49:50', '开始阅读解忧杂货店');
INSERT INTO `timeline` VALUES ('3', '6', '2015-01-21 10:50:17', '参加一日一书活动');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '个人书籍');
INSERT INTO `type` VALUES ('2', '武侠小说');
INSERT INTO `type` VALUES ('3', '玄幻小说');
INSERT INTO `type` VALUES ('4', '言情小说');
INSERT INTO `type` VALUES ('6', '战争小说');
INSERT INTO `type` VALUES ('7', '悬疑小说');
INSERT INTO `type` VALUES ('8', '推理小说');
INSERT INTO `type` VALUES ('9', '穿越小说');
INSERT INTO `type` VALUES ('10', '探险小说');
INSERT INTO `type` VALUES ('11', '历史小说');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(64) COLLATE utf8_bin NOT NULL,
  `picture` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `level` int(1) DEFAULT '2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'Alix', '1d664d9cd4e1512844c55fc0f26662dc', 'upload\\user\\45.jpg', '2');
INSERT INTO `user` VALUES ('8', 'ming', '55b311d5fac8fbd2667c6995c289f5ff', 'upload\\user\\45.jpg', '2');
INSERT INTO `user` VALUES ('10', 'oli', '33ac0d39328e33fa8619b59fc05a1480', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('12', 'root', 'b4b8daf4b8ea9d39568719e1e320076f', 'upload\\user\\44.jpg', '1');
INSERT INTO `user` VALUES ('13', 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', 'upload\\user\\45.jpg', '1');
INSERT INTO `user` VALUES ('14', 'manyu', 'mamy', 'upload\\user\\45.jpg', '2');
INSERT INTO `user` VALUES ('15', 'mayuu', 'mayuualix', 'upload\\user\\45.jpg', '2');
INSERT INTO `user` VALUES ('16', 'minture', 'minture', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('17', 'derek', 'derek', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('20', 'aaaa', 'aaaa', 'upload\\user\\45.jpg', '2');
INSERT INTO `user` VALUES ('21', 'ee', 'aa', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('50', '2', '2', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('51', '3', '4', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('52', '4', '4', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('53', '4', '4', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('55', '4', '4', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('56', '5', '5', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('57', '6', '6', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('58', '1', '1', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('59', '1', '1', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('60', '1', '1', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('62', '2', '2', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('63', '3', '3', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('64', '4', '4', 'upload\\user\\44.jpg', '2');
INSERT INTO `user` VALUES ('65', 'me', 'me', 'upload\\user\\2.jpg', '2');
INSERT INTO `user` VALUES ('66', '2', '12', 'upload\\user\\3.jpg', '2');
INSERT INTO `user` VALUES ('67', 'af', 'afd', 'upload\\user\\3.jpg', '2');
