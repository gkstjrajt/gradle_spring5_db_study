INSERT INTO MEMBER(EMAIL, PASSWORD, NAME, REGDATE) VALUES('test@test.co.kr', '1111', 'test', '2020-09-28');

SELECT ID, EMAIL, PASSWORD, NAME, REGDATE FROM MEMBER;

UPDATE MEMBER
   SET EMAIL
 WHERE ID = ?;

DELETE FROM MEMBER WHERE ID = ?;