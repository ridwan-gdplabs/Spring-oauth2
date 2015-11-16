-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2015 at 05:16 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `banking`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE IF NOT EXISTS `accounts` (
  `account_id` varchar(255) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_id`, `balance`) VALUES
('0987654321', 100000),
('1234567890', 12345);

-- --------------------------------------------------------

--
-- Table structure for table `accounts_users`
--

CREATE TABLE IF NOT EXISTS `accounts_users` (
  `id` int(11) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts_users`
--

INSERT INTO `accounts_users` (`id`, `account_id`, `username`) VALUES
(1, '1234567890', 'ridwan'),
(2, '0987654321', 'ridwan');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `authority` varchar(255) NOT NULL,
  `method` varchar(255) NOT NULL,
  `scope` varchar(255) NOT NULL,
  `ant_pattern` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`authority`, `method`, `scope`, `ant_pattern`) VALUES
('ROLE_READ_ACCOUNTS', 'GET', 'READ', '/accounts'),
('ROLE_READ_ME', 'GET', 'READ', '/me'),
('ROLE_READ_TRANSFERS', 'GET', 'READ', '/accounts/*/transfers'),
('ROLE_WRITE_TRANSFERS', 'POST', 'WRITE', '/accounts/*/transfers');

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`, `enabled`) VALUES
(1, 'administrators', 1),
(2, 'users', 1);

-- --------------------------------------------------------

--
-- Table structure for table `group_authorities`
--

CREATE TABLE IF NOT EXISTS `group_authorities` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `authority` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_authorities`
--

INSERT INTO `group_authorities` (`id`, `group_id`, `authority`) VALUES
(1, 1, 'ROLE_READ_ACCOUNTS'),
(2, 2, 'ROLE_READ_ME'),
(3, 1, 'ROLE_READ_TRANSFERS'),
(4, 2, 'ROLE_READ_TRANSFERS'),
(5, 2, 'ROLE_WRITE_TRANSFERS');

-- --------------------------------------------------------

--
-- Table structure for table `group_members`
--

CREATE TABLE IF NOT EXISTS `group_members` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_members`
--

INSERT INTO `group_members` (`id`, `group_id`, `username`) VALUES
(1, 1, 'ridwan'),
(2, 2, 'ridwan2');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_token`
--

CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oauth_access_token`
--

INSERT INTO `oauth_access_token` (`token_id`, `token`, `authentication_id`, `user_name`, `client_id`, `authentication`, `refresh_token`) VALUES
('543c262a2d216b22142b0bf3ebb63b24', 0xaced0005737200436f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f4175746832416363657373546f6b656e0cb29e361b24face0200064c00156164646974696f6e616c496e666f726d6174696f6e74000f4c6a6176612f7574696c2f4d61703b4c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b4c000c72656672657368546f6b656e74003f4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f636f6d6d6f6e2f4f417574683252656672657368546f6b656e3b4c000573636f706574000f4c6a6176612f7574696c2f5365743b4c0009746f6b656e547970657400124c6a6176612f6c616e672f537472696e673b4c000576616c756571007e000578707372001e6a6176612e7574696c2e436f6c6c656374696f6e7324456d7074794d6170593614855adce7d002000078707372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000150dc373c8d787372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e71007e0002787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c756571007e0005787074002463336536336161332d386638612d343239332d616661352d3738633234356430336636367371007e0009770800000150dc373c8b78737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6c656374696f6e3b7870737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000023f4000000000000174000577726974657874000662656172657274002432633832663563382d373566322d346638622d626661342d313066393637626635623738, 'd873f0aeb66f15a14e5947cc7f149d14', 'ridwan2', 'testID', 0xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001400200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b787074000a524f4c455f61646d696e7871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0014787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00124c000573636f706571007e00147870740006746573744944737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00127870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000772696477616e3278737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001740005777269746578017371007e0023770c000000103f400000000000017371007e000d740006636c69656e74787371007e001a3f4000000000000077080000001000000000787070737200116a6176612e7574696c2e54726565536574dd98509395ed875b03000078707077040000000174000d746573745f7265736f75726365787371007e0023770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001400200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000177040000000171007e000f7871007e0031737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001a3f400000000000067708000000080000000271007e001c71007e001d71007e001e71007e001f780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001400200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00144c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e00207371007e002a737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000140020000787077040000000171007e000f787074000772696477616e32, '4ff77651bc9881f7679277a0046c1d8f'),
('d33f55eac255a5f58995a0219c108ac7', 0xaced0005737200436f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f4175746832416363657373546f6b656e0cb29e361b24face0200064c00156164646974696f6e616c496e666f726d6174696f6e74000f4c6a6176612f7574696c2f4d61703b4c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b4c000c72656672657368546f6b656e74003f4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f636f6d6d6f6e2f4f417574683252656672657368546f6b656e3b4c000573636f706574000f4c6a6176612f7574696c2f5365743b4c0009746f6b656e547970657400124c6a6176612f6c616e672f537472696e673b4c000576616c756571007e000578707372001e6a6176612e7574696c2e436f6c6c656374696f6e7324456d7074794d6170593614855adce7d002000078707372000e6a6176612e7574696c2e44617465686a81014b59741903000078707708000001510e946ff7787372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e71007e0002787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c756571007e0005787074002439346132353866332d303963332d343539362d616638612d3233343537333232376239627371007e00097708000001510ecb5e7278737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c0001637400164c6a6176612f7574696c2f436f6c6c656374696f6e3b7870737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000023f4000000000000174000577726974657874000662656172657274002434643432303535662d333462312d346233662d393134642d303839333933386163383565, 'ae3bd5aa496f7e672c19d1063eec66d8', 'ridwan', 'testID', 0xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000002770400000002737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001400200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b7870740012524f4c455f524541445f4143434f554e54537371007e000d740013524f4c455f524541445f5452414e53464552537871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0016787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00144c000573636f706571007e00167870740006746573744944737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00147870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000672696477616e78737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001740005777269746578017371007e0025770c000000103f400000000000017371007e000d740006636c69656e74787371007e001c3f4000000000000077080000001000000000787070737200116a6176612e7574696c2e54726565536574dd98509395ed875b03000078707077040000000174000d746573745f7265736f75726365787371007e0025770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001400200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000277040000000271007e000f71007e00117871007e0033737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001c3f400000000000067708000000080000000271007e001e71007e001f71007e002071007e0021780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001400200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00164c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e00227371007e002c737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000140020000787077040000000271007e000f71007e0011787074000672696477616e, 'e4cb1dbd384ab7afef719ee0c67d42ab');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_client_details`
--

CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oauth_client_details`
--

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES
('mobileClientId', 'resourceId', NULL, 'write', 'password', '', 'client', 3600, NULL, '{}', ''),
('serverClientId', 'resourceId', 'serverClientSecret', 'write', 'password,refresh_token,implicit,authorization_code,client_credentials', '', 'client', 999999, 9999999, '{}', ''),
('testID', 'test_resource', 'testSecret', 'write', 'password,refresh_token,implicit,authorization_code,client_credentials', 'localhost:8080/oauth/authorize', 'client', 3600, 7200, '{}', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_client_token`
--

CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_code`
--

CREATE TABLE IF NOT EXISTS `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_token`
--

CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oauth_refresh_token`
--

INSERT INTO `oauth_refresh_token` (`token_id`, `token`, `authentication`) VALUES
('4ff77651bc9881f7679277a0046c1d8f', 0xaced00057372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b787074002463336536336161332d386638612d343239332d616661352d3738633234356430336636367372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000150dc373c8b78, 0xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001400200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b787074000a524f4c455f61646d696e7871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0014787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00124c000573636f706571007e00147870740006746573744944737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00127870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000772696477616e3278737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001740005777269746578017371007e0023770c000000103f400000000000017371007e000d740006636c69656e74787371007e001a3f4000000000000077080000001000000000787070737200116a6176612e7574696c2e54726565536574dd98509395ed875b03000078707077040000000174000d746573745f7265736f75726365787371007e0023770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001400200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000177040000000171007e000f7871007e0031737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001a3f400000000000067708000000080000000271007e001c71007e001d71007e001e71007e001f780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001400200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00144c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e00207371007e002a737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000140020000787077040000000171007e000f787074000772696477616e32),
('29341d6f8a347f152b0493dd1871a8c3', 0xaced00057372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b787074002430623037616331332d376165342d343637312d623334642d6232653131396166646232637372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000150fb36e61e78, 0xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000002770400000002737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001400200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b787074000a524f4c455f41444d494e7371007e000d740009524f4c455f555345527871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0016787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00144c000573636f706571007e00167870740006746573744944737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00147870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000672696477616e78737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001740005777269746578017371007e0025770c000000103f400000000000017371007e000d740006636c69656e74787371007e001c3f4000000000000077080000001000000000787070737200116a6176612e7574696c2e54726565536574dd98509395ed875b03000078707077040000000174000d746573745f7265736f75726365787371007e0025770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001400200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000277040000000271007e000f71007e00117871007e0033737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001c3f400000000000067708000000080000000271007e001e71007e001f71007e002071007e0021780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001400200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00164c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e00227371007e002c737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000140020000787077040000000271007e000f71007e0011787074000672696477616e),
('e4cb1dbd384ab7afef719ee0c67d42ab', 0xaced00057372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744578706972696e674f417574683252656672657368546f6b656e2fdf47639dd0c9b70200014c000a65787069726174696f6e7400104c6a6176612f7574696c2f446174653b787200446f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e636f6d6d6f6e2e44656661756c744f417574683252656672657368546f6b656e73e10e0a6354d45e0200014c000576616c75657400124c6a6176612f6c616e672f537472696e673b787074002439346132353866332d303963332d343539362d616638612d3233343537333232376239627372000e6a6176612e7574696c2e44617465686a81014b59741903000078707708000001510ecb5e7278, 0xaced0005737200416f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f417574683241757468656e7469636174696f6ebd400b02166252130200024c000d73746f7265645265717565737474003c4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f4f4175746832526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c000764657461696c737400124c6a6176612f6c616e672f4f626a6563743b787000737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00047870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000002770400000002737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001400200014c0004726f6c657400124c6a6176612f6c616e672f537472696e673b787074000a524f4c455f41444d494e7371007e000d740009524f4c455f555345527871007e000c707372003a6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e4f41757468325265717565737400000000000000010200075a0008617070726f7665644c000b617574686f72697469657371007e00044c000a657874656e73696f6e7374000f4c6a6176612f7574696c2f4d61703b4c000b726564697265637455726971007e000e4c00077265667265736874003b4c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f546f6b656e526571756573743b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000d726573706f6e7365547970657371007e0016787200386f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e426173655265717565737436287a3ea37169bd0200034c0008636c69656e74496471007e000e4c001172657175657374506172616d657465727371007e00144c000573636f706571007e00167870740006746573744944737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654d6170f1a5a8fe74f507420200014c00016d71007e00147870737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f400000000000067708000000080000000274000a6772616e745f7479706574000870617373776f7264740008757365726e616d6574000672696477616e78737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0009737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001740005777269746578017371007e0025770c000000103f400000000000017371007e000d740006636c69656e74787371007e001c3f4000000000000077080000001000000000787070737200116a6176612e7574696c2e54726565536574dd98509395ed875b03000078707077040000000174000d746573745f7265736f75726365787371007e0025770c000000103f40000000000000787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001400200024c000b63726564656e7469616c7371007e00054c00097072696e636970616c71007e00057871007e0003017371007e00077371007e000b0000000277040000000271007e000f71007e00117871007e0033737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e001c3f400000000000067708000000080000000271007e001e71007e001f71007e002071007e0021780070737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001400200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00164c000870617373776f726471007e000e4c0008757365726e616d6571007e000e7870010101017371007e00227371007e002c737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000140020000787077040000000271007e000f71007e0011787074000672696477616e);

-- --------------------------------------------------------

--
-- Table structure for table `transfer`
--

CREATE TABLE IF NOT EXISTS `transfer` (
  `transaction_id` varchar(50) NOT NULL,
  `date_time` datetime NOT NULL,
  `amount` double NOT NULL,
  `sender` varchar(50) NOT NULL,
  `recipient` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transfer`
--

INSERT INTO `transfer` (`transaction_id`, `date_time`, `amount`, `sender`, `recipient`) VALUES
('1234567890', '2015-05-15 09:16:46', 10000, '1234567890', '0987654321'),
('8a2f17544d56c9c8014d56cac3250000', '2015-05-15 15:57:18', 100000, '1234567890', '246800'),
('8a2f17544d56cb3e014d56cb66b50000', '2015-05-15 15:58:00', 100000, '1234567890', '246800'),
('8a2f17544d56cb3e014d56cc50700001', '2015-05-15 15:59:00', 10, '1234567890', '246800'),
('8a2f17544d56cb3e014d56ce9ead0002', '2015-05-15 16:01:31', 10, '1234567890', '0987654321'),
('8a2f17544d56cb3e014d56cef7610003', '2015-05-15 16:01:54', 10, '1234567890', '0987654321'),
('8a2f17544d56cf2b014d56cf43010000', '2015-05-15 16:02:13', 10, '1234567890', '0987654321'),
('8a2f17544d56cf2b014d56cf5c960001', '2015-05-15 16:02:20', 10, '1234567890', '0987654321'),
('8a2f17544d56e15d014d56e2d2680000', '2015-05-15 16:23:35', 10, '1234567890', '0987654321'),
('8a2f17544d56e335014d56e37e5f0000', '2015-05-15 16:24:19', 10, '1234567890', '0987654321'),
('8a2f17544d56e93f014d56e9dcb60000', '2015-05-15 16:31:17', 10, '1234567890', '0987654321'),
('8a2f17544d56eb3c014d56eb6a8b0000', '2015-05-15 16:32:58', 10, '1234567890', '0987654321');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(256) NOT NULL DEFAULT '',
  `password` varchar(256) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`, `name`) VALUES
('andhika', 'andhikaPass', 1, ''),
('michell', 'michellPass', 1, ''),
('ridwan', '$2a$08$lC45IuBa90oXZ4MFWndIcuRy8fAXlgd.IQjiQmGFyfVvG9QoR9Kwe', 1, 'Ridwan Budiman'),
('ridwan2', '$2a$08$lC45IuBa90oXZ4MFWndIcuRy8fAXlgd.IQjiQmGFyfVvG9QoR9Kwe', 1, 'Ridwan2'),
('user1', 'user1Pass', 1, ''),
('user2', 'user2Pass', 1, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `accounts_users`
--
ALTER TABLE `accounts_users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`authority`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`group_id`);

--
-- Indexes for table `group_authorities`
--
ALTER TABLE `group_authorities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_members`
--
ALTER TABLE `group_members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `oauth_client_details`
--
ALTER TABLE `oauth_client_details`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `transfer`
--
ALTER TABLE `transfer`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts_users`
--
ALTER TABLE `accounts_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `group_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `group_authorities`
--
ALTER TABLE `group_authorities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `group_members`
--
ALTER TABLE `group_members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
