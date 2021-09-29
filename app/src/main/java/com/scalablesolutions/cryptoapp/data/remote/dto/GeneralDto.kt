package com.scalablesolutions.cryptoapp.data.remote.dto

data class GeneralDto(
    val background: BackgroundDto,
    val overview: OverviewDto,
    val regulation: RegulationDto,
    val roadmap: List<RoadmapDto>
)