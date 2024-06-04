start transaction;
use `acme-dancer`;

revoke all privileges on `acme-dancer`.* from 'acme-user'@'%';
revoke all privileges on `acme-dancer`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `acme-dancer`;
commit;