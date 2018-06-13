
select par.id,par.title,par.iconCls,par.url,
    chl.id chlId,chl.title chlTitle,chl.url chlUrl,chl.iconCls chlIconCls from
    cmfz_menu par
    inner join
    cmfz_menu chl
    on par.id=chl.parentId



select id ,account,islogoff isLogoff,nickname nickName,
      pass,salt,regTime, photoSrc  from cmfz_manager



 select id,pictureName,picturePath,message,
 uploadTime,status,size,md5Code from cmfz_picture
  order by id
  limit 8,4

select issue.id issueid,issue.name issuename,issue.createDate issuecd
    ,issue.score issuescore,issue.author issueauthor,
    issue.brief issuebr,issue.img issueimg,issue.counts issuecouts,audio.id audioid,
    audio.name,audio.size,audio.url,audio.audioTime,audio.md5Code,audio.issueId parentId
    from cmfz_issue issue
    inner join
    cmfz_audio audio
    on issue.id=audio.issueId
    order by issueid
    limit 0,6



select id,phoneNum,username userName,password,salt,dharmaName,province,
       city,sex,sign,headPic,status,FROM_UNIXTIME( date, '%Y-%m-%d' ),FROM_UNIXTIME(regDate, '%Y-%m-%d' )
       from cmfz_user
       order by id
       LIMIT 1,10

select  province,count(*) value  from cmfz_user
       where sex='y'
      group by province

select  province,count(*) value  from cmfz_user
       where sex='n'
      group by province


select from_unixtime(regDate,'%Y-%m-%d') from cmfz_user
where  (unix_timestamp(current_timestamp )-regDate)/60/60/24 between 14 and 21

select  (unix_timestamp(current_timestamp)-1527858101)/60/60/24 from dual

select from_unixtime(1527858101,'%Y-%m-%d') from dual



 select id,FROM_UNIXTIME(createTime,'%Y-%m-%d') as time,
         message text,managerAccount from cmfz_log
         order by id





update cmfz_user set status='n' where id=1;
update cmfz_user set status='n' where id=2;

select UNIX_TIMESTAMP(current_date) from dual;

  select id,phoneNum,username userName,password,salt,dharmaName,province,
       city,sex,sign,headPic,status,FROM_UNIXTIME( date, '%Y-%m-%d') as date,FROM_UNIXTIME(regDate, '%Y-%m-%d' ) as regDate,masterId
       from cmfz_user
       where id!=1