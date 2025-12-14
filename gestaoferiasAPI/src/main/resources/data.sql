
-- SERVIDOR
INSERT INTO servidor (id, nome, matricula) VALUES
(1, 'João da Silva', '123456');

-- STATUS
INSERT INTO status_solicitacao (id, descricao) VALUES
(1, 'Solicitado'),
(2, 'Aprovado'),
(3, 'Concluído');

-- PERIODO DE FÉRIAS
INSERT INTO periodo_ferias (id, data_inicio, data_fim, servidor_id, status_id) VALUES
(1, '2024-01-10', '2024-01-24', 1, 3),
(2, '2025-02-05', '2025-02-19', 1, 2);

-- PAGAMENTO
INSERT INTO pagamento (id, tipo_pagamento, valor_bruto, valor_liquido, data_pagamento, periodo_id) VALUES
(1, 'Férias', 5000.00, 4000.00, '2024-01-05', 1);
