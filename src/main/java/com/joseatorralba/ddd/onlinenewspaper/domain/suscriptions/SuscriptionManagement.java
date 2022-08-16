package com.joseatorralba.ddd.onlinenewspaper.domain.suscriptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SuscriptionManagement {

	private List<User> users = new ArrayList<>();
}
