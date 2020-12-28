SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[Requests](
	[RequestId] [int] IDENTITY(1,1) NOT NULL,
	[TourId] [int] NOT NULL,
	[isApproved] [bit] NOT NULL,
	[ClientId] [int] NOT NULL,
	[Date] [datetime] NOT NULL,
 CONSTRAINT [PK_Requests] PRIMARY KEY CLUSTERED 
(
	[RequestId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
ALTER TABLE [dbo].[Requests]  WITH CHECK ADD  CONSTRAINT [FK_Requests_Clients] FOREIGN KEY([ClientId])
REFERENCES [dbo].[Clients] ([ClientId]);
ALTER TABLE [dbo].[Requests] CHECK CONSTRAINT [FK_Requests_Clients];
ALTER TABLE [dbo].[Requests]  WITH CHECK ADD  CONSTRAINT [FK_Requests_Tours] FOREIGN KEY([TourId])
REFERENCES [dbo].[Tours] ([TourId])
ON DELETE CASCADE
ALTER TABLE [dbo].[Requests] CHECK CONSTRAINT [FK_Requests_Tours];
