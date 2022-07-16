-- DB생성
CREATE DATABASE opot;

-- 사용자 생성
CREATE USER 'opot'@'localhost' IDENTIFIED BY 'opot0801';

-- 사용자 권한 부여
GRANT ALL PRIVILEGES ON opot.* TO 'opot'@'localhost';

-- 사용자 권한 확인하기
SHOW GRANTS FOR 'opot'@'localhost';

-- DB 사용
USE opot;

COMMIT;