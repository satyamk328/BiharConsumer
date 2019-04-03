package com.digital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketVO {
private Long ticketId;
private Long custId;
private Long operatorId;
private Long busId;
private String dbTripId;


}
