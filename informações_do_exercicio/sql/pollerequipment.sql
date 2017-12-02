CREATE TABLE pollerequipment
(
    address character varying(50) NOT NULL,
    status boolean DEFAULT false,
    CONSTRAINT pk_poller PRIMARY KEY (address)
)

create sequence seq_poller;