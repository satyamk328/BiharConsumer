select * from ( 

SELECT route.RouteId,sm.BusId,ss.Displayname Source,se.displayname Destination,sm.DepartureDate,sm.DepartureTime,sm.ArrivalDate,sm.ArrivalTime, CONCAT(ts.stopid,'::',re.stopid,'::',ts.stopnumber,'::',re.stopnumber) tripid,
(select sum(tdis.distance) from route_city_stop_mapping tdis join city_master city on (city.cityid = tdis.cityid) where  tdis.stopnumber > ts.stopnumber and tdis.stopnumber <= re.stopnumber) Distance, 
(select sum(tdis.duration) from route_city_stop_mapping tdis join city_master city on (city.cityid = tdis.cityid) where  tdis.stopnumber > ts.stopnumber and tdis.stopnumber <= re.stopnumber) Duration,
(select sum(tdis.basefare) from route_city_stop_mapping tdis join city_master city on (city.cityid = tdis.cityid) where  tdis.stopnumber > ts.stopnumber and tdis.stopnumber <= re.stopnumber) BaseFare
  -- ts.arrivaldate arrivaldate,ts.arrivaltime arrivaltime,`schedule_master``schedule_master`re.arrivaldate departuredate,re.arrivaltime departuretime, 
  
FROM route_master route JOIN route_city_stop_mapping ts ON (ts.RouteId = route.RouteId) 
JOIN city_master ss ON (ss.CityId = ts.CityId) 
JOIN route_city_stop_mapping re ON (re.RouteId = route.RouteId) 
JOIN city_master se ON (se.CityId = re.CityId) 
JOIN schedule_master sm on (sm.RouteId=route.RouteId)
WHERE lower(ss.displayname) LIKE '%delhi%' AND lower(se.displayname) LIKE 'alla%' and sm.Departuredate='2019-04-30'

and re.arrivaldate::TEXT=? AND ts.stopnumber < re.stopnumber \
) tt JOIN bus_operators_route_mapping bsm on (bsm.routeid=tt.routeid) JOIN bus_master bs on (bs.busid=bsm.busid) JOIN bus_layout bl ON (bl.id=bsm.layoutid)


