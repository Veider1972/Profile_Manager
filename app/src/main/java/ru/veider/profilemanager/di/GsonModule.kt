package ru.veider.profilemanager.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val gsonModule = module {
	single<Gson>{
		GsonBuilder()
//			.registerTypeHierarchyAdapter(LocalTime::class.java, LocalTimeAdapter())
//			.registerTypeHierarchyAdapter(LocalDate::class.java, LocalDateAdapter())
//			.registerTypeHierarchyAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
//			.registerTypeAdapter(Color::class.java, ColorAdapter())
//			.registerTypeAdapter(Dp::class.java, DpAdapter())
//			.registerTypeAdapter(TextUnit::class.java, TextUnitAdapter())
			.create()
	}
}