package com.example.data

import com.example.ui.components.AvatarType

object TcaRepository {

    // 1. Mitos y Realidades sobre TCA
    val mythsList: List<MythItem> = listOf(
        MythItem(
            id = "myth_1",
            myth = "Los TCA son una elección o una etapa pasajera para llamar la atención.",
            reality = "Los TCA son trastornos de salud mental y físicos complejos reconocidos médicamente, no una elección voluntaria.",
            explanation = "Nadie elige desarrollar un TCA. Intervienen factores genéticos, neurobiológicos, psicológicos y del entorno social. Requieren comprensión, empatía y tratamiento profesional interdisciplinario.",
            avatar = AvatarType.CURIOSO
        ),
        MythItem(
            id = "myth_2",
            myth = "Solo las personas con un peso corporal muy bajo pueden tener un TCA.",
            reality = "Los TCA ocurren en personas de cualquier tamaño corporal, peso, género o edad.",
            explanation = "El sufrimiento emocional y las alteraciones en la relación con la comida no se determinan por una báscula. La mayoría de las personas con TCA no tienen bajo peso evidente, lo cual a menudo retrasa una ayuda necesaria.",
            avatar = AvatarType.POSITIVO
        ),
        MythItem(
            id = "myth_3",
            myth = "Las familias y madres/padres son los culpables de los TCA de sus hijos.",
            reality = "Las familias no causan el trastorno; son la principal red de apoyo y recuperación.",
            explanation = "La evidencia clínica moderna desmiente la culpabilización familiar. Un entorno afectivo, informado y sin juicios es el factor de protección más poderoso para el acompañamiento.",
            avatar = AvatarType.EMPATICO
        ),
        MythItem(
            id = "myth_4",
            myth = "Hacer dieta restrictiva es un hábito inofensivo para cuidar la salud.",
            reality = "Las dietas restrictivas en la infancia y adolescencia son uno de los mayores factores de riesgo.",
            explanation = "La restricción alimentaria altera las señales naturales de hambre y saciedad, aumenta la ansiedad, el aislamiento social y puede desencadenar ciclos de obsesión o atracón.",
            avatar = AvatarType.MOTIVADOR
        ),
        MythItem(
            id = "myth_5",
            myth = "Los TCA solo afectan a mujeres adolescentes.",
            reality = "Afectan a personas de todas las edades, identidades de género y contextos socioeconómicos.",
            explanation = "Varones, niños pequeños y personas adultas también transitan TCA, a menudo enfrentando mayores barreras para consultar por estereotipos de género.",
            avatar = AvatarType.CREATIVO
        ),
        MythItem(
            id = "myth_6",
            myth = "Basta con 'comer bien' o tener fuerza de voluntad para curarse.",
            reality = "La recuperación implica un abordaje integral de las emociones, la mente y el cuerpo.",
            explanation = "El síntoma con la comida es solo la punta del iceberg. Abordar el malestar interno, la autoexigencia y el dolor subyacente con un equipo de salud es fundamental.",
            avatar = AvatarType.DIVERTIDO
        )
    )

    // 2. Señales de Alerta Cuidadosas (Sin Diagnosticar)
    val warningSigns: List<WarningSign> = listOf(
        WarningSign(
            category = "Emocionales y Psicológicas",
            title = "Aumento brusco de la autoexigencia e irritabilidad",
            description = "Cambios anímicos frecuentes, angustia intensa alrededor de las comidas o aislamiento de amigos y planes compartidos.",
            actionTip = "Abrí un espacio tranquilo para preguntar cómo se siente por dentro, sin centrarte en lo que come."
        ),
        WarningSign(
            category = "Emocionales y Psicológicas",
            title = "Preocupación constante y excesiva por la imagen corporal",
            description = "Comentarios frecuentes de insatisfacción, descalificación hacia su propio cuerpo o comparación constante con publicaciones de redes.",
            actionTip = "Validá sus sentimientos y reforzá sus talentos, valores y cualidades que nada tienen que ver con el aspecto físico."
        ),
        WarningSign(
            category = "Conductuales",
            title = "Evitación reiterada de comidas familiares o escolares",
            description = "Justificaciones continuas como 'ya comí antes' o 'tengo que estudiar solo/a en la habitación'.",
            actionTip = "Propiciá momentos compartidos agradables en la mesa, sin interrogatorios ni presión."
        ),
        WarningSign(
            category = "Conductuales",
            title = "Reglas rígidas o rituales con los alimentos",
            description = "Clasificación estricta de alimentos en 'prohibidos', necesidad de cortar en trozos mínimos o incomodidad ante la comida imprevista.",
            actionTip = "Fomentá en casa una relación flexible y relajada con la alimentación, evitando clasificar platos como 'buenos' o 'malos'."
        ),
        WarningSign(
            category = "Físicas y de Bienestar",
            title = "Cansancio persistente, mareos o sensación de frío constante",
            description = "Cambios en el nivel de energía, dificultades para concentrarse en la escuela o molestias digestivas recurrentes.",
            actionTip = "Agendá una consulta médica pediátrica o general para una evaluación clínica integral y empática."
        ),
        WarningSign(
            category = "En el Ámbito Escolar",
            title = "Aislamiento en recreos y cambio en el rendimiento",
            description = "Dejar de compartir la merienda con pares, rechazo a actividades físicas grupales o perfeccionismo escolar paralizante.",
            actionTip = "Los docentes pueden activar el canal del equipo de orientación y generar un acercamiento privado y afectuoso."
        )
    )

    // 3. Guías de Conversación Respetuosa (Familias y Docentes)
    val conversationGuides: List<ConversationGuide> = listOf(
        ConversationGuide(
            context = "Al notar que evita momentos compartidos de comida",
            recommendedPhrase = "'He notado que últimamente te sentís con menos ganas de compartir la mesa. Te quiero mucho y me importa saber cómo estás de ánimo en estos días.'",
            avoidPhrase = "'¡Tenés que comer como todo el mundo! ¡Mirá qué flaco/a estás y cómo nos preocupás!'",
            whyExplanation = "Poner el foco en el bienestar emocional y el vínculo afectivo genera confianza, mientras que los reproches o comentarios sobre el cuerpo activan defensas y culpa.",
            avatar = AvatarType.EMPATICO
        ),
        ConversationGuide(
            context = "Cuando expresa disgusto o incomodidad con su apariencia",
            recommendedPhrase = "'Comprendo que te sientas así y que a veces la presión sea muy pesada. Tu cuerpo merece cuidado y respeto tal como es, y tus valores y risa valen muchísimo más.'",
            avoidPhrase = "'¡No digas tonterías, si estás genial! ¡No te preocupes que no engordaste nada!'",
            whyExplanation = "Minimizar o contra-opinar sobre el cuerpo refuerza el mandato de que la apariencia es lo que determina el valor de la persona.",
            avatar = AvatarType.CURIOSO
        ),
        ConversationGuide(
            context = "Al hablar sobre comidas en la mesa familiar",
            recommendedPhrase = "'La comida nos nutre, nos da energía para jugar y estudiar, y también es un momento para disfrutar juntos y conversar de nuestro día.'",
            avoidPhrase = "'Esto engorda mucho, mejor no comas', o 'Hoy rompí la dieta, mañana compenso no cenando.'",
            whyExplanation = "Los comentarios de dietas y compensaciones de los adultos son internalizados por niños y adolescentes como normas de valor y culpa.",
            avatar = AvatarType.MOTIVADOR
        ),
        ConversationGuide(
            context = "Charla privada docente - estudiante ante cambios observados",
            recommendedPhrase = "'Te veo un poco más callado/a en clase y me preocupa tu bienestar. La escuela es un lugar seguro para vos. ¿Hay algo en lo que pueda apoyarte hoy?'",
            avoidPhrase = "'Noté que no estás comiendo el sándwich en el recreo, ¿tenés algún problema con la comida?'",
            whyExplanation = "El rol docente es brindar contención y enlace con el equipo de orientación escolar sin invadir ni etiquetar de inmediato la conducta alimentaria.",
            avatar = AvatarType.POSITIVO
        )
    )

    // 4. Dinámicas y Talleres para el Aula
    val classroomActivities: List<ClassroomActivity> = listOf(
        ClassroomActivity(
            id = "act_inicial_1",
            title = "El Jardín de las Emociones y el Cuidado",
            level = EducationalLevel.INICIAL,
            durationMinutes = 25,
            objectives = listOf(
                "Identificar emociones básicas (alegría, calma, tristeza, enojo)",
                "Reconocer sensaciones de cuidado mutuo y amabilidad",
                "Comprender que pedir ayuda es un superpoder"
            ),
            materials = listOf("Tarjetas ilustradas de los avatares", "Cojines para ronda de diálogo"),
            stepByStep = listOf(
                "Presentación en ronda de Curioso, Empático y Positivo.",
                "Cada niño/a elige el personaje que representa cómo se siente hoy.",
                "Juego gestual: imitamos una sonrisa grande, un bostezo de descanso y un abrazo de oso.",
                "Cierre con canción suave sobre cómo cuidarnos entre todos."
            ),
            reflectionQuestions = listOf(
                "¿Qué cosas nos hacen sentir tranquilos y felices?",
                "¿A quién podemos pedirle un abrazo o ayuda cuando nos sentimos tristes?"
            ),
            avatar = AvatarType.EMPATICO
        ),
        ClassroomActivity(
            id = "act_primaria_1",
            title = "Mural: Mis Talentos No Tienen Talla",
            level = EducationalLevel.PRIMARIA,
            durationMinutes = 40,
            objectives = listOf(
                "Desvincular la autoestima de la apariencia física",
                "Promover el respeto por la diversidad de cuerpos y capacidades",
                "Prevenir burlas y comentarios corporales entre pares"
            ),
            materials = listOf("Cartulina grande", "Marcadores y témperas", "Siluetas geométricas variadas"),
            stepByStep = listOf(
                "Conversación disparadora: ¿Qué hace que una persona sea única y especial?",
                "Cada estudiante escribe dentro de una forma geométrica 3 cosas que admira de sí mismo (creatividad, valentía, buen humor, lealtad).",
                "Armado cooperativo del mural escolar en el aula.",
                "Compromiso de aula: 'Cuidamos nuestras palabras; no opinamos sobre los cuerpos ajenos'."
            ),
            reflectionQuestions = listOf(
                "¿Por qué el valor de una persona nunca depende de su forma o tamaño?",
                "¿Cómo nos sentimos cuando un amigo nos elogia por algo que hacemos o pensamos?"
            ),
            avatar = AvatarType.POSITIVO
        ),
        ClassroomActivity(
            id = "act_secundaria_1",
            title = "Laboratorio Crítico: Filtros, Algoritmos y Redes",
            level = EducationalLevel.SECUNDARIA,
            durationMinutes = 50,
            objectives = listOf(
                "Analizar la construcción artificial de los ideales estéticos en medios digitales",
                "Comprender el modelo de negocio de los algoritmos y la comparación constante",
                "Desarrollar herramientas de autocuidado digital e higiene en redes"
            ),
            materials = listOf("Proyector o fichas de análisis de perfiles digitales", "Hojas de trabajo reflexivo"),
            stepByStep = listOf(
                "Exposición disparadora de imágenes con retoques digitales y ángulos profesionales.",
                "Debate en grupos pequeños: ¿Qué porcentaje de lo que vemos en redes refleja la vida real?",
                "Mapeo de emociones: ¿Cómo nos sentimos tras 40 minutos de scrollear feeds de perfección?",
                "Diseño de un plan de desintoxicación digital y seguimiento de cuentas que aporten bienestar real."
            ),
            reflectionQuestions = listOf(
                "¿Por qué la comparación con imágenes retocadas genera tanta insatisfacción?",
                "¿Qué medidas prácticas podemos tomar hoy para que nuestras redes sean un espacio seguro?"
            ),
            avatar = AvatarType.CREATIVO
        )
    )

    // 5. Biblioteca Central de Recursos
    val libraryResources: List<ResourceItem> = listOf(
        ResourceItem(
            id = "res_1",
            title = "Guía Institucional: Detección Temprana y Protocolo Escolar",
            audience = AudienceType.DOCENTES,
            level = EducationalLevel.TODOS,
            topic = ResourceTopic.PREVENCION_AULA,
            format = ResourceFormat.GUIA_PRACTICA,
            estimatedTime = "15 min de lectura",
            description = "Pautas claras para equipos directivos, docentes y gabinetes psicopedagógicos para intervenir a tiempo, proteger la confidencialidad y coordinar con los centros de salud.",
            instructions = listOf(
                "1. Observar con prudencia y registrar hechos concretos sin juicios personales.",
                "2. No intentar diagnosticar ni confrontar al estudiante.",
                "3. Convocar al equipo directivo y de orientación escolar de la institución.",
                "4. Articular una reunión cálida y sin culpabilización con la familia.",
                "5. Facilitar los contactos de servicios sanitarios de referencia comunitaria."
            ),
            materials = listOf("Ficha de protocolo institucional", "Cuaderno de seguimiento interno"),
            professionalSource = "Guía de Prevención en Ámbitos Escolares - Ministerio de Salud y FEACAB",
            avatar = AvatarType.CURIOSO
        ),
        ResourceItem(
            id = "res_2",
            title = "Decálogo del Hogar: Crianza Respetuosa sin Peso-centrismo",
            audience = AudienceType.FAMILIAS,
            level = EducationalLevel.TODOS,
            topic = ResourceTopic.ACOMPANAMIENTO,
            format = ResourceFormat.TALLER_FAMILIAR,
            estimatedTime = "10 min de lectura",
            description = "Diez recomendaciones prácticas para crear un clima seguro en casa donde la comida sea nutritiva y placentera, y el cuerpo no sea motivo de examen.",
            instructions = listOf(
                "Evitar elogios o críticas hacia los cambios de peso de cualquier persona.",
                "No categorizar comidas como 'premios', 'castigos' o 'pecados'.",
                "Promover la actividad física por juego y alegría, no por quemar calorías.",
                "Escuchar activamente las angustias y frustraciones cotidianas de hijos e hijas."
            ),
            materials = listOf("Imprimible para la heladera familiar"),
            professionalSource = "Asociación Española para el Estudio de los Trastornos de la Conducta Alimentaria (AEETCA)",
            avatar = AvatarType.EMPATICO
        ),
        ResourceItem(
            id = "res_3",
            title = "Infografía: Factores Protectores en la Infancia y Adolescencia",
            audience = AudienceType.GENERAL,
            level = EducationalLevel.TODOS,
            topic = ResourceTopic.CONCEPTO_TCA,
            format = ResourceFormat.INFOGRAFIA,
            estimatedTime = "5 min de lectura",
            description = "Resumen visual de los elementos que blindan el bienestar emocional: autoestima diversa, pensamiento crítico frente a los medios, red afectiva y diálogo abierto.",
            instructions = listOf(
                "Revisar individualmente o compartir en reuniones de madres y padres.",
                "Utilizar como afiche en salas de profesores o carteleras comunitarias."
            ),
            materials = listOf("Ficha infográfica a color"),
            professionalSource = "Sociedad Argentina de Pediatría (SAP) y NEDA",
            avatar = AvatarType.POSITIVO
        ),
        ResourceItem(
            id = "res_4",
            title = "Taller: Alfabetización Digital y Presión Estética",
            audience = AudienceType.ESTUDIANTES,
            level = EducationalLevel.SECUNDARIA,
            topic = ResourceTopic.REDES_SOCIALES,
            format = ResourceFormat.ACTIVIDAD_AULA,
            estimatedTime = "45 min",
            description = "Propuesta participativa para desentrañar cómo las redes sociales monetizan la inseguridad corporal y cómo construir un feed protector.",
            instructions = listOf(
                "Presentación de ejemplos de iluminación, ángulos y filtros.",
                "Debate plenario guiado.",
                "Ejercicio práctico de 'limpieza de seguidos'."
            ),
            materials = listOf("Dispositivo para proyectar o material impreso"),
            professionalSource = "Instituto de la Juventud y Salud Mental Comunitaria",
            avatar = AvatarType.CREATIVO
        ),
        ResourceItem(
            id = "res_5",
            title = "Cuento y Ficha: 'La Ronda de Todas las Formas'",
            audience = AudienceType.ESTUDIANTES,
            level = EducationalLevel.INICIAL,
            topic = ResourceTopic.DIVERSIDAD_CORPORAL,
            format = ResourceFormat.ACTIVIDAD_AULA,
            estimatedTime = "20 min",
            description = "Relato ilustrado con los avatares donde una estrella, un círculo y un triángulo descubren que su belleza está en ser diferentes.",
            instructions = listOf(
                "Lectura compartida por la maestra o el maestro.",
                "Dramatización lúdica con títeres de cartón de los personajes.",
                "Dibujo libre del personaje favorito."
            ),
            materials = listOf("Cuento ilustrado", "Hojas y crayones"),
            professionalSource = "Programa de Educación Emocional en la Primera Infancia",
            avatar = AvatarType.DIVERTIDO
        ),
        ResourceItem(
            id = "res_6",
            title = "Guía: Cómo Hablar con un/a Amigo/a que Está Sufriendo",
            audience = AudienceType.ESTUDIANTES,
            level = EducationalLevel.SECUNDARIA,
            topic = ResourceTopic.ACOMPANAMIENTO,
            format = ResourceFormat.GUIA_PRACTICA,
            estimatedTime = "8 min de lectura",
            description = "Respuestas y herramientas para adolescentes que observan que un compañero de curso la está pasando mal y no saben cómo actuar.",
            instructions = listOf(
                "Recordar que no sos su médico ni su terapeuta: sos su amigo/a.",
                "Estar presente sin presionar ni vigilar su comida.",
                "Animarlo/a con afecto a hablar con una persona adulta de confianza.",
                "No guardar secretos que pongan en peligro su salud."
            ),
            materials = listOf("Folleto de consulta rápida"),
            professionalSource = "Federación Española de Asociaciones de TCA y Líneas de Ayuda Juveniles",
            avatar = AvatarType.EMPATICO
        )
    )

    // 6. Líneas y Servicios de Ayuda Profesional Oficiales
    val emergencyContacts: List<EmergencyContact> = listOf(
        EmergencyContact(
            country = "Argentina",
            countryCode = "AR",
            serviceName = "Línea 102 (Derechos de Niños, Niñas y Adolescentes)",
            phoneNumber = "102",
            description = "Servicio público, confidencial y gratuito de orientación y acompañamiento emocional para infancias y juventudes.",
            schedule = "Atención las 24 horas, los 365 días del año",
            website = "argentina.gob.ar/desarrollosocial/linea102"
        ),
        EmergencyContact(
            country = "Argentina",
            countryCode = "AR",
            serviceName = "ALUBA (Asociación de Lucha contra la Bulimia y la Anorexia)",
            phoneNumber = "0800-222-5822 / (011) 4306-0033",
            description = "Entidad especializada en orientación familiar, grupos de apoyo y derivación clínica en TCA.",
            schedule = "Lunes a Viernes de 9 a 18 hs",
            website = "aluba.org.ar"
        ),
        EmergencyContact(
            country = "España",
            countryCode = "ES",
            serviceName = "Línea 024 de Atención a la Conducta Suicida y Crisis Emocional",
            phoneNumber = "024",
            description = "Servicio telefónico oficial, gratuito y confidencial del Ministerio de Sanidad de España.",
            schedule = "Atención permanente 24/7",
            website = "sanidad.gob.es"
        ),
        EmergencyContact(
            country = "España",
            countryCode = "ES",
            serviceName = "FEACAB (Federación Española de Asociaciones de TCA)",
            phoneNumber = "900 102 411",
            description = "Teléfono de orientación y asesoramiento especializado para familias, educadores y personas afectadas.",
            schedule = "Lunes a Viernes de 10 a 14 hs",
            website = "feacab.org"
        ),
        EmergencyContact(
            country = "México",
            countryCode = "MX",
            serviceName = "Línea de la Vida (Secretaría de Salud)",
            phoneNumber = "800 911 2000",
            description = "Atención médica y psicológica especializada en salud mental, crisis emocional y adicciones en todo el país.",
            schedule = "Gratuito las 24 horas, los 365 días",
            website = "gob.mx/salud/conadic"
        ),
        EmergencyContact(
            country = "México",
            countryCode = "MX",
            serviceName = "Comenzar de Nuevo A.C.",
            phoneNumber = "+52 (81) 8378-3506",
            description = "Institución mexicana sin fines de lucro especializada en prevención, educación y tratamiento de TCA.",
            schedule = "Lunes a Viernes de 9 a 18 hs",
            website = "comenzardenuevo.org"
        ),
        EmergencyContact(
            country = "Chile",
            countryCode = "CL",
            serviceName = "Salud Responde (MINSAL)",
            phoneNumber = "600 360 7777",
            description = "Plataforma telefónica del Ministerio de Salud de Chile para orientación profesional de salud general y mental.",
            schedule = "Atención permanente las 24 horas",
            website = "saludresponde.minsal.cl"
        ),
        EmergencyContact(
            country = "Colombia",
            countryCode = "CO",
            serviceName = "Línea 106 de Apoyo Psicológico y Salud Mental",
            phoneNumber = "106",
            description = "Línea de ayuda gratuita para niños, jóvenes y cuidadores en situaciones de angustia o crisis emocional.",
            schedule = "24 horas al día, los 7 días de la semana",
            website = "saludcapital.gov.co"
        )
    )

    // 7. Preguntas Frecuentes Institucionales (FAQ)
    val faqList: List<Pair<String, String>> = listOf(
        Pair(
            "¿Cuál es el objetivo principal de esta aplicación?",
            "Esta plataforma tiene una finalidad exclusivamente educativa y de concientización comunitaria. Busca desestigmatizar los trastornos de la conducta alimentaria, promover el respeto por la diversidad corporal, brindar pautas de escucha respetuosa y facilitar la detección precoz y derivación oportuna a profesionales de la salud."
        ),
        Pair(
            "¿Esta aplicación realiza diagnósticos o tests de detección médica?",
            "No, de ninguna manera. Los TCA son patologías complejas que requieren una evaluación médica y psicológica presencial e interdisciplinaria. Esta herramienta no ofrece cuestionarios diagnósticos ni sustituye la mirada clínica especializada."
        ),
        Pair(
            "¿Por qué no se mencionan cifras de peso, calorías ni dietas?",
            "Siguiendo las recomendaciones internacionales de prevención (OMS, NEDA, FEACAB), el abordaje preventivo debe evitar cualquier contenido que pueda funcionar como detonante o disparador de conductas nocivas. Promovemos una alimentación flexible, basada en el bienestar y libre de culpas."
        ),
        Pair(
            "¿Qué debe hacer una docente si sospecha de una situación de riesgo?",
            "Debe observar con prudencia, sin etiquetar al estudiante ni forzar confesiones. El protocolo indica recurrir al equipo de orientación escolar de la institución y convocar a la familia en un marco de escucha afectuosa para recomendar una consulta con el equipo de salud."
        ),
        Pair(
            "¿Cómo se resguardan la privacidad y los datos de las personas usuarias?",
            "La aplicación no recopila información personal, nombres reales, fotografías ni registros corporales. Es un espacio completamente seguro, confidencial y libre de rastreo."
        )
    )
}
