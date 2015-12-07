-- INICIO AJUSTES INICIAIS JULIANA
INSERT INTO defaultfile (datacriacao, dataedicao, version, ativo, caminho, dominiodefaultfile_id, ordem) VALUES (TO_DATE('2015-06-09 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-06-09 09:26:25','YYYY-MM-DD HH24:MI:SS'), 0, true, '/cit-contratos-web/assets/css/module-custom.css', 1, 8);

ALTER SEQUENCE pessoa_id_seq RESTART WITH 5475;

ALTER SEQUENCE parceiro_id_seq RESTART WITH 5475;

UPDATE estruturaorganizacional SET orgao_id = 1 WHERE estruturaorganizacionalparent_id = 1;
UPDATE estruturaorganizacional SET orgao_id = 1 WHERE estruturaorganizacionalparent_id IN (SELECT id FROM estruturaorganizacional WHERE estruturaorganizacionalparent_id = 1);
UPDATE estruturaorganizacional SET orgao_id = 2 WHERE estruturaorganizacionalparent_id = 2;
UPDATE estruturaorganizacional SET orgao_id = 2 WHERE estruturaorganizacionalparent_id IN (SELECT id FROM estruturaorganizacional WHERE estruturaorganizacionalparent_id = 2);
UPDATE estruturaorganizacional SET orgao_id = 3 WHERE estruturaorganizacionalparent_id = 3;
UPDATE estruturaorganizacional SET orgao_id = 3 WHERE estruturaorganizacionalparent_id IN (SELECT id FROM estruturaorganizacional WHERE estruturaorganizacionalparent_id = 3);

UPDATE contacontabil SET orgao_id = 3;
-- FIM AJUSTES INICIAIS JULIANA


 /*SQL GERADOR DO UPDATE DO parent_id da ESTRUTURA COM BASE cd_ua_sup DO ASI
select 'UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = '
    || pai.id  || ' where codigo = '
    || chr(39) || filho.codigo
    || chr(39) || ';' as "-- atualiza parent_id com base cd_ua_sup do asi"
  from public.estruturaorganizacional pai inner join public.estruturaorganizacional filho
    on pai.codigo = filho.classificacao;
*/

-- INICIO UPDATE DO parent_id da ESTRUTURA COM BASE cd_ua_sup DO ASI
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 152 where codigo = '115069';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 153 where codigo = '115070';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 152 where codigo = '115071';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 152 where codigo = '115072';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115073';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115074';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 178 where codigo = '115075';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115076';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115077';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115078';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115081';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115082';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115083';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115092';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115087';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115090';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115093';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115094';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 176 where codigo = '115095';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115097';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115098';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115099';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115100';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115101';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 177 where codigo = '115102';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 184 where codigo = '115103';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115104';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115105';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 258 where codigo = '115106';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 257 where codigo = '115108';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 258 where codigo = '115109';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 257 where codigo = '115110';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 257 where codigo = '115111';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 285 where codigo = '115112';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115113';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 402 where codigo = '115114';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 194 where codigo = '115115';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 402 where codigo = '115116';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115118';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 303 where codigo = '115119';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 283 where codigo = '115120';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115121';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 403 where codigo = '115122';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115123';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 304 where codigo = '115125';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115126';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 303 where codigo = '115127';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115128';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 210 where codigo = '115129';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 194 where codigo = '115130';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 177 where codigo = '115096';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115085';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115131';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115132';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 210 where codigo = '115133';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115135';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115136';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 184 where codigo = '115137';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115138';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115139';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 303 where codigo = '115140';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115141';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115144';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 417 where codigo = '115160';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 178 where codigo = '115163';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115164';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115165';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115190';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 127 where codigo = '115219';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115179';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115180';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 285 where codigo = '115181';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115182';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 260 where codigo = '115183';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115186';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115188';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115189';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115191';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115193';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115195';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115196';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115197';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115198';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115199';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115200';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115201';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115202';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 272 where codigo = '115203';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115204';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115206';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 176 where codigo = '115207';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 317 where codigo = '115161';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 227 where codigo = '115185';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115171';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115194';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 181 where codigo = '115170';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115208';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 393 where codigo = '115210';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 393 where codigo = '115211';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115213';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115214';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 194 where codigo = '115215';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 161 where codigo = '115216';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115217';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115218';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 304 where codigo = '115220';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 356 where codigo = '115222';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115223';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115225';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115226';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115227';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115228';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115230';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115234';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115235';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115236';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115237';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 202 where codigo = '115238';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 183 where codigo = '115239';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 393 where codigo = '115243';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115244';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115231';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115232';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115233';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115246';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 178 where codigo = '115257';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115259';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '000003';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 285 where codigo = '115224';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115253';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115252';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 290 where codigo = '115251';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 125 where codigo = '115242';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115209';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 202 where codigo = '115267';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 403 where codigo = '115268';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115272';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 281 where codigo = '115274';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115276';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 304 where codigo = '115278';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115084';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115089';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115006';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115004';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115005';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115007';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115008';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115011';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115012';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115013';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115014';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 96 where codigo = '115015';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 94 where codigo = '115016';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115017';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115019';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 139 where codigo = '115056';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115022';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 178 where codigo = '115024';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115028';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115030';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 117 where codigo = '115032';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 124 where codigo = '115036';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 127 where codigo = '115041';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115042';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 127 where codigo = '115043';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115044';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 127 where codigo = '115045';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115047';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115048';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115049';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115050';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115051';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 242 where codigo = '115038';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115025';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115027';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115029';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115021';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115046';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 117 where codigo = '115031';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 117 where codigo = '115033';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 117 where codigo = '115034';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 117 where codigo = '115035';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115052';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115057';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115054';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115055';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115058';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115059';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115060';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115061';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 151 where codigo = '115062';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 151 where codigo = '115063';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 151 where codigo = '115064';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115065';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 151 where codigo = '115066';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115067';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115068';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115362';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115356';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115145';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115148';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 361 where codigo = '115149';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115150';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115151';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115152';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 354 where codigo = '115153';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115154';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115156';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115157';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115158';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115322';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115312';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 317 where codigo = '115314';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115317';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115318';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 354 where codigo = '115319';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 417 where codigo = '115320';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 238 where codigo = '115321';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 283 where codigo = '115325';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 360 where codigo = '115333';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 167 where codigo = '115335';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115336';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115339';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 353 where codigo = '115338';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 417 where codigo = '115340';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 285 where codigo = '115341';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 297 where codigo = '115342';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 402 where codigo = '115344';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 402 where codigo = '115345';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 238 where codigo = '115316';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115323';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 283 where codigo = '115343';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 141 where codigo = '115346';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 231 where codigo = '115315';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 283 where codigo = '115324';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 98 where codigo = '115334';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 303 where codigo = '115347';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 383 where codigo = '115348';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 383 where codigo = '115349';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 143 where codigo = '115350';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115351';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 443 where codigo = '115366';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 443 where codigo = '115367';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 443 where codigo = '115368';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 443 where codigo = '115369';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115364';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 443 where codigo = '115365';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115355';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115357';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115358';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115359';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115360';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115361';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115363';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 180 where codigo = '115023';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '000009';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 226 where codigo = '115373';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '000008';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 242 where codigo = '115162';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 106 where codigo = '115370';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115088';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 382 where codigo = '115377';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 451 where codigo = '000010';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115378';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115379';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 434 where codigo = '115380';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 160 where codigo = '115159';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 317 where codigo = '115375';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 317 where codigo = '115374';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115371';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 430 where codigo = '115353';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 430 where codigo = '115354';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 135 where codigo = '115381';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 462 where codigo = '115240';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 317 where codigo = '115382';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 108 where codigo = '115026';
UPDATE public.estruturaorganizacional SET estruturaorganizacionalparent_id = 430 where codigo = '115352';
-- FIM UPDATE DO parent_id da ESTRUTURA COM BASE cd_ua_sup DO ASI


-- INICIO INSERT CONFIGURACAO PARA ESTRUTURAS --
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
                VALUES (1, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 47);
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
                VALUES (2, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 47);
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
                VALUES (3, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 47);
INSERT INTO configuracao(id, datacriacao, dataedicao, version, dominioStatusBemExtraviado_id)
                VALUES (4, LOCALTIMESTAMP, LOCALTIMESTAMP, 0, 47);
-- FIM INSERT CONFIGURACAO PARA ESTRUTURAS --


-- INICIO UPDATE ESTRUTURAS DO TIPO ORGAO COM CONFIGURACAO --
UPDATE estruturaorganizacional SET configuracao_id=1, orgao_id = null WHERE id = 1;
UPDATE estruturaorganizacional SET configuracao_id=2, orgao_id = null WHERE id = 2;
UPDATE estruturaorganizacional SET configuracao_id=3, orgao_id = null WHERE id = 3;
UPDATE estruturaorganizacional SET configuracao_id=4, orgao_id = null WHERE id = 4;
-- INICIO UPDATE ESTRUTURAS DO TIPO ORGAO COM CONFIGURACAO --


-- INICIO UPDATE LOCALIZACAO PARA ORGAO 3 --
update localizacao set orgao_id = 3;
-- FIM UPDATE LOCALIZACAO PARA ORGAO 3 --

-- INICIO UPDATE CONFIGURACAOSISTEMA PARA ORGAO 3 --
UPDATE configuracaosistema SET orgao_id = 3;
-- FIM UPDATE CONFIGURACAOSISTEMA PARA ORGAO 3 --

-- INICIO UPDATE PARAMETROSISTEMA PARA INICIALIZAR CARGA PORTAL.JSON --
UPDATE parametrosistema SET valor = 'true' where id = 15;
-- FIM UPDATE PARAMETROSISTEMA PARA INICIALIZAR CARGA PORTAL.JSON --

-- INICIO SEQUENCE ESTRUTURAORGAO E ESTRUTURAORGANIZACIONAL --
ALTER SEQUENCE estruturaorgao_id_seq RESTART WITH 3000;
CREATE SEQUENCE estruturaorganizacional_id_seq RESTART WITH 3000;
-- FIM SEQUENCE ESTRUTURAORGAO E ESTRUTURAORGANIZACIONAL --

-- INICIO UPDATE ESTRUTURAORGANIZACIONALCOMPRA
update bempatrimonial set estruturaorganizacionalcompra_id = 514;
-- FIM UPDATE ESTRUTURAORGANIZACIONALCOMPRA

-- INICIO AJUSTES PARA QUE O TERMO DE RESPONSABILIDADE POR DETENTOR LISTE BENS PATRIMONIAIS
--FUNCAO
INSERT INTO funcao(id, datacriacao, dataedicao, version, nome)
VALUES (500, TO_DATE('2015-01-01 09:26:25','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2015-01-01 10:26:27','YYYY-MM-DD HH24:MI:SS'), 0, 'GERENTE');

UPDATE parceiro_colaborador SET funcao_id = 500;

--PESSOA

UPDATE pessoa SET orgao_id = 3;
update pessoa set dominio_pessoa = 31 where dominio_pessoa = 1;

UPDATE parceiro_colaborador SET estruturaorganizacional_id = 3;

-- FIM AJUSTES PARA QUE O TERMO DE RESPONSABILIDADE POR DETENTOR LISTE BENS PATRIMONIAIS

-- INICIO SETA SE A ESTRUTURA VAI POSSUIR BEM PATRIMONIAL

do $$
declare
v_eoid numeric;
v_count numeric := 0;
v_possui boolean := false;
begin
  for v_eoid, v_possui in select estruturaorganizacionalatual_id, case when count(*)>0 then true when count(*)=0 then false end from bempatrimonial group by estruturaorganizacionalatual_id
  loop

    update estruturaorganizacional set possuibempatrimonial = v_possui where id = v_eoid;
  end loop;
end$$;

ALTER TABLE baixaalmoxarifado ALTER COLUMN numerobaixa DROP NOT NULL

-- FIM SETA SE A ESTRUTURA VAI POSSUIR BEM PATRIMONIAL

